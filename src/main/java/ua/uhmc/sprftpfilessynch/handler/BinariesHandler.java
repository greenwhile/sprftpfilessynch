package ua.uhmc.sprftpfilessynch.handler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.uhmc.sprftpfilessynch.config.Constants;
import ua.uhmc.sprftpfilessynch.grib2.Grib2;
import ua.uhmc.sprftpfilessynch.grib2.Grib2FileName;
import ua.uhmc.sprftpfilessynch.grib2.body.Body;
import ua.uhmc.sprftpfilessynch.grib2.header.Header;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class BinariesHandler {

//    @Value("${local.directory.binaries.name}")
//    private String localDirectoryBinaries;

//    @Value("${local.directory.grib2.name}")
//    private String localDirectoryGrib2;

    //  home/loc/Videos/sprftpfilessynch/src/main/resources/binaries

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private File localDirectoryBinaries = new File(new File(new File(new File(new File(new File(System.getProperty("user.home"),
            "Videos"),
            "sprftpfilessynch"),
            "src"),
            "main"),
            "resources"),
            "binaries");

    private File localDirectoryGrib2 = new File(new File(new File(new File(new File(new File(System.getProperty("user.home"),
            "Videos"),
            "sprftpfilessynch"),
            "src"),
            "main"),
            "resources"),
            "grib2");
    static Integer yy = 0;
    private Grib2FileName fileName;
    private byte [] bytes;

    public BinariesHandler() {
    }
    public List<File> getBinaryDataFilesList(/*String folderPath*/){
        System.out.println("binaries: " + localDirectoryBinaries + " grib2: " + localDirectoryGrib2);
        File[] folderEntries = localDirectoryBinaries.listFiles();
        List<File> files = new ArrayList<File>();
        if(folderEntries.length > 0){
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    List<File> subFolderEntries = getBinaryDataFilesList();
//                    System.out.println(" files sz " + subFolderEntries.size());
                    for (File subEntry : subFolderEntries) {
                        if(subEntry.isFile())
                            files.add(subEntry);
                    }
                }
                if(entry.isFile()){
                    files.add(entry);
                }
            }
        }
        return files;
    }

    private String getString(byte [] b, Integer [] ind){
        byte [] arr = new byte[3];
        int count = 0;
        for(int i = ind[0]; i <= ind[1]; i++){
            if(count < 3){
                arr[count] = b[i];
                System.out.print(" =>>>>>> " + count + "  " + b[i]);
                count++;
            } else{
                break;
            }
        }
        return new String(arr, StandardCharsets.US_ASCII);
    }

    public byte [] cutIntoGrib2Files(byte [] bytes, Path pathToBinaryFile) throws IOException {

        Integer total_len = bytes.length;
        Integer grb2_len = -1;

        Grib2 grib2 = new Grib2(bytes, pathToBinaryFile);

        Header header = grib2.getHeader();
        Body body = grib2.getBody();

        Integer header_len = header.getLength();
        System.out.println("\nlen: " + header_len);

        Grib2FileName filename = grib2.getT1T2A1();
        System.out.println(" filename " +
                filename.getSuperWmoHeader() + " " +
                filename.getT1T2A1() + " " +
                filename.getA2() + " " +
                filename.getDatasource() + " " +
                filename.getObservation() + " " +
                filename.getLevel());

        String grib2FileName = grib2.getFilename().get();
        System.out.println(grib2FileName);

        Integer[] headerIndexes = header.getHeaderIndexes();

        if(headerIndexes[0] == null && headerIndexes[1] == null){
            return bytes;
        } else {
            System.out.println("*******************************************");
            System.out.println("H ind: " + headerIndexes[0] + "  " + headerIndexes[1]);
            for(int i = headerIndexes[0]; i <= headerIndexes[1]; i++){
                System.out.print((char) bytes[i]);
            }

            Integer bodyStartIndex = body.getStartIndex();
            Integer bodyEndIndex = body.getEndIndex();
            yy++;

            byte[] gribFileBytes = grib2.getGrib2Grid(bytes, bodyStartIndex, bodyEndIndex);
            grb2_len = gribFileBytes.length;

            File f = new File(localDirectoryGrib2, grib2.getFilename().get());
            System.out.println("grb2 filename:  " + f.getPath());
            grib2.writeIntoBinaryFile(gribFileBytes, f.getPath());
            rabbitTemplate.convertAndSend(Constants.METEO_DATA_EXCHANGE,Constants.GRIB2_ROUTING_KEY, grib2.getFilename().get());
            bytes = grib2.getGrib2BulletinContent(bytes, bodyEndIndex);
        }
        System.out.println("total_len: " + total_len + " " + " grb2_len: " + grb2_len);

        return total_len >= grb2_len ? cutIntoGrib2Files(bytes, pathToBinaryFile) : bytes;
    }

}

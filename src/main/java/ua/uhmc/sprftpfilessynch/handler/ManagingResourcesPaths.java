package ua.uhmc.sprftpfilessynch.handler;

import java.io.File;

public class ManagingResourcesPaths {

    private String path;

    public ManagingResourcesPaths(String path) {
        this.path = path;
    }

//    public File getFilePath(){
//        String[] pathParticles = path.split(".");
//        String startingPath = System.getProperty("user.home");
//        for(String particle : pathParticles){
//            System.out.println(path.matches("(?<=\\.)(.*?)(?=\\.)"));
//            Pattern pattern = Pattern.compile("(?<=\\.)(.*?)(?=\\.)");
//            Matcher matcher = pattern.matcher(path);
//            if (matcher.find())
//            {
//                System.out.println(matcher.group(1));
//            }
//            File file = new File(new File(startingPath), particle);
//            return file;
//        }
//    }

    private File localDirectoryGrib2 = new File(new File(new File(new File(new File(new File(System.getProperty("user.home"),
            "IdeaProjects"),
            "sprftpfilessynch"),
            "src"),
            "main"),
            "resources"),
            "grib2");

    private File localDirectoryBinaries = new File(new File(new File(new File(new File(new File(System.getProperty("user.home"),
            "IdeaProjects"),
            "sprftpfilessynch"),
            "src"),
            "main"),
            "resources"),
            "binaries");

}

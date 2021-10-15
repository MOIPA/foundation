package com.company.parseBinaryFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 解析文件下数据包
 */
public class Parser {
    public static void main(String[] args) {
//        doDownLoad();
//        doFilterVehiclePackage();
//        doParseFifthPackage();
//        doFilterPackage(4);
//        doParseZeroPackage();
        doParse("Package4");
//        doFilterVehicle(5);
    }

    /**
     * 下载所有nifi数据包
     */
    public static void doDownLoad() {
        List<String> fileNames = readFileContent("C:\\Users\\TR\\Downloads\\fileList.json");
        List<String> events = readFileContent("C:\\Users\\TR\\Downloads\\event.json");
//        fileNames.forEach(System.out::println);
        for (int i = 0; i < fileNames.size(); i++) {
            try {
                downLoadFromUrl("http://192.168.57.11:8181/nifi-api/provenance-events/" + events.get(i) + "/content/output?clusterNodeId=" + fileNames.get(i),
                        "clusterNodeId=" + fileNames.get(i) + "_eventId=" + events.get(i) + "_" + i, "C:\\Users\\TR\\Downloads\\autoDownLoadPackage");
            } catch (Exception e) {
            }
        }
    }

    public static List<String> readFileContent(String fileName) {
        File file = new File(fileName);
        List<String> fileNames = new LinkedList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                fileNames.add(tempStr);
            }
            reader.close();
            return fileNames;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return fileNames;
    }

    /**
     * 过滤在不在redis车列表内包
     */
    public static void doFilterVehiclePackage() {
        int counter = 0;
        List<String> files = getFiles("C:\\Users\\TR\\Downloads\\autoDownLoadPackage");
        List<Integer> filter = new LinkedList<>();
        filter.add(1);
        filter.add(2);
        filter.add(3);
        filter.add(5);
        filter.add(8);
        filter.add(9);
        filter.add(10);
        filter.add(12);
        filter.add(15);
        filter.add(22);
        filter.add(23);
        filter.add(27);
        filter.add(29);
        filter.add(32);
        filter.add(35);
//        files.forEach(System.out::println);
        DataInputStream dis = null;
        for (String file : files) {
            try {
                dis = new DataInputStream(new FileInputStream(file));
                int temp;
                while ((temp = dis.read()) != -1) {
                    counter++;
//                    if (counter == 11 || counter == 12) {
//                        if (counter == 11) {
//                            System.out.println("file:" + file);
//                            System.out.print(temp + " ");
//                        } else {
//                            System.out.print(temp);
//                        }
//                    }
                    if (counter == 12 && !filter.contains(temp)) {
                        System.out.println("file:" + file);
                        System.out.println(temp);
                        File filtedFile = new File(file.replace("autoDownLoadPackage", "FiltedPackage"));
//                        if (!filtedFile.exists())filtedFile.createNewFile();
                        copyFileUsingJava7Files(new File(file), filtedFile);
                    }
                }
//                System.out.println();
                counter = 0;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 过滤某车
     */
    public static void doFilterVehicle(int vehicle) {
        int counter = 0;
        List<String> files = getFiles("C:\\Users\\TR\\Downloads\\autoDownLoadPackage");

//        files.forEach(System.out::println);
        DataInputStream dis = null;
        for (String file : files) {
            try {
                dis = new DataInputStream(new FileInputStream(file));
                int temp;
                while ((temp = dis.read()) != -1) {
                    counter++;
//                    if (counter == 11 || counter == 12) {
//                        if (counter == 11) {
//                            System.out.println("file:" + file);
//                            System.out.print(temp + " ");
//                        } else {
//                            System.out.print(temp);
//                        }
//                    }
                    if (counter == 12 && temp == vehicle) {
                        System.out.println("file:" + file);
                        System.out.println(temp);
                        File filtedFile = new File(file.replace("autoDownLoadPackage", "Vehicle"+vehicle));
//                        if (!filtedFile.exists())filtedFile.createNewFile();
                        copyFileUsingJava7Files(new File(file), filtedFile);
                    }
                }
//                System.out.println();
                counter = 0;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 过滤对应package
     */
    public static void doFilterPackage(int packageIndex) {
        int counter = 0;
        List<String> files = getFiles("C:\\Users\\TR\\Downloads\\autoDownLoadPackage");
        DataInputStream dis = null;
        for (String file : files) {
            try {
                dis = new DataInputStream(new FileInputStream(file));
                int temp;
                while ((temp = dis.read()) != -1) {
                    if (counter == 7 && temp == packageIndex) {
                        System.out.println("file:" + file);
                        System.out.println(temp);
                        File filtedFile = new File(file.replace("autoDownLoadPackage", "Package" + packageIndex));
                        copyFileUsingJava7Files(new File(file), filtedFile);
                    }
                    counter++;
                }
                counter = 0;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 分析5号包
     */
    public static void doParseFifthPackage() {
        int counter = 0;
        List<String> files = getFiles("C:\\Users\\TR\\Downloads\\FifthPackage");
        DataInputStream dis = null;
        for (String file : files) {
            try {
                dis = new DataInputStream(new FileInputStream(file));
                int temp;
                while ((temp = dis.read()) != -1) {
                    if (counter == 354) {
                        System.out.println("file:" + file);
                        System.out.println(temp);
//                        File filtedFile = new File(file.replace("autoDownLoadPackage", "FifthPackage"));
//                        copyFileUsingJava7Files(new File(file), filtedFile);
                    } else if (counter == 355) System.out.println(temp);
                    counter++;
                }
                counter = 0;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 分析ATC端口的包
     */
    public static void doParseZeroPackage() {
        int counter = 0;
        List<String> files = getFiles("C:\\Users\\TR\\Downloads\\FifthPackage");
        DataInputStream dis = null;
        for (String file : files) {
            try {
                dis = new DataInputStream(new FileInputStream(file));
                int temp;
                while ((temp = dis.read()) != -1) {
                    if (counter == 344) {
                        System.out.println("file:" + file);
                        System.out.println("PIC端口的数据值（从324字节开始到408字节十进制输出）：");
                        System.out.print(temp);
                    } else if (counter > 344 && counter <= 428) System.out.print(temp);
                    counter++;
                }
                counter = 0;
                System.out.println("\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 分析包
     */
    public static void doParse(String packageName) {
        int counter = 0;
        List<String> files = getFiles("C:\\Users\\TR\\Downloads\\"+packageName);
//        files.forEach(System.out::println);
        DataInputStream dis = null;
        for (String file : files) {
            try {
                dis = new DataInputStream(new FileInputStream(file));
                int temp;
                while ((temp = dis.read()) != -1) {
//                    if (counter == 11 || counter == 12) {
//                        if (counter == 11) {
//                            System.out.println("file:" + file);
//                            System.out.print(temp + " ");
//                        } else {
//                            System.out.print(temp);
//                        }
//                    }
                    if (counter == 357 && temp !=0) {
                        System.out.println("file:" + file);
                        System.out.println(Integer.toBinaryString(temp));
                    }
                    counter++;
                }
//                System.out.println();
                counter = 0;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void copyFileUsingJava7Files(File source, File dest)
            throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println("info:" + url + " download success");
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }
}

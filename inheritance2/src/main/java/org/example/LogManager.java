package org.example;

public class LogManager {
    public void log(int logType) { //1- DB, 2-File, 3-Email
        if (logType==1) {
            System.out.println("dbye loglandı.");
        } else if (logType==2) {
            System.out.println("dosyaya loglandı.");
        }else {
            System.out.println("eposta gönderildi.");
        }
    }
}

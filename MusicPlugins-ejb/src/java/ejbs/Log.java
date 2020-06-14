/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;

/**
 *
 * @author zuzu
 */
@Singleton
@LocalBean
public class Log {

    private String log = "";
    private String logPrev = "";

    public String getLog() {
        return log;
    }

    public void setLogParam(String classN, String method, String data) {
        log += classN + "::" + method + "::" + data + "<br> ";
    }

    public void setLog(String classN, String method) {
        log += "\n" + classN + "::" + method + "<br> ";
    }

    @Schedule(second = "*/5", minute = "*", hour = "*")
    private void writeFile() throws IOException {
        File file = new File("C:\\Users\\zuzu\\Documents\\NetBeansProjects\\MusicPlugins");
        BufferedWriter output = null;
        output = new BufferedWriter(new FileWriter(file));
        output.write(getLog());
        output.close();
    }

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void noInteraction() {
        if (log.length() == logPrev.length()) {
            setLog("SingletonLog", "noInteraction");
        } else {
            logPrev = getLog();
        }
    }
}

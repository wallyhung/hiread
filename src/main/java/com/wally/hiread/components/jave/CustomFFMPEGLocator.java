package com.wally.hiread.components.jave;

import it.sauronsoftware.jave.FFMPEGLocator;

import java.net.URL;

/**
 * Created by eric on 06/06/2017.
 */
public class CustomFFMPEGLocator extends FFMPEGLocator{
    @Override
    protected String getFFMPEGExecutablePath() {
        String os = System.getProperty("os.name").toLowerCase();

        URL url = null;
        if(os.indexOf("mac") != -1){
            url = CustomFFMPEGLocator.class.getResource("/components/jave/mac/ffmpeg");
        } else if (os.indexOf("windows") != -1){
            url = CustomFFMPEGLocator.class.getResource("/components/jave/windows/ffmpeg.exe");
        } else {
            url = CustomFFMPEGLocator.class.getResource("/components/jave/linux/ffmpeg");
        }

        return url.getPath();
    }
}

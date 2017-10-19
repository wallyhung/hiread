package com.wally.hiread.components.jave;

import it.sauronsoftware.jave.*;

import java.io.File;

/**
 * Created by eric on 06/06/2017.
 */
public class AudioConverter {
    /**
     * convert audio
     * @param source
     * @param target
     * @return
     */
    public static void convertAmr2Mp3(File source, File target) throws EncoderException {
        if (source == null){
            throw new RuntimeException("source file not exist.");
        }

        String sourceFileName = source.getName();
        String sourceFileNameWithoutSuffix = sourceFileName.indexOf(".") > 0 ? sourceFileName.substring(0, sourceFileName.lastIndexOf(".")) : sourceFileName;

        File newTarget = null;
        String newTargetPath = null;
        if(target == null){
            newTargetPath = source.getParent() + File.separator + sourceFileNameWithoutSuffix + ".mp3";
        } else {
            newTargetPath = target.getPath() + File.separator + sourceFileNameWithoutSuffix + ".mp3";
        }
        newTarget = new File(newTargetPath);

        AudioAttributes audioAttributes = new AudioAttributes();
        audioAttributes.setCodec("libmp3lame");
        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setFormat("mp3");
        encodingAttributes.setAudioAttributes(audioAttributes);

        Encoder encoder = new Encoder(new CustomFFMPEGLocator());
        encoder.encode(source, newTarget, encodingAttributes);
    }
}

package com.bbs.albbspringai.controller;

import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@RestController
public class albbSoundController {

    @Autowired
    private  DashScopeSpeechSynthesisModel dashScopeSpeechSynthesisModel;

    private static final String TEXT = "你好，我是一只游鱼";
    private static final String PATH = "E:\\springai\\springai\\albbSpringAi\\src\\main\\resources\\tts\\mp3.mp3";

    @GetMapping("/albb/sound")
    public void tts() throws IOException {
    //使用构建器模式创建DashscopeSpeechsynthesisoptions实例并设置参数
        DashScopeSpeechSynthesisOptions options = DashScopeSpeechSynthesisOptions.builder()
                .speed(1.0f)   //语速
                .pitch(1.0)    //音调
                .volume(50)   //音量
                .sampleRate(16000)  //采样率
                .build();

        SpeechSynthesisResponse response = dashScopeSpeechSynthesisModel.call(new SpeechSynthesisPrompt(
                TEXT, options
        ));

        File file = new File(PATH);

//创建文件输出流
        try (FileOutputStream fos = new FileOutputStream(file)){
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}

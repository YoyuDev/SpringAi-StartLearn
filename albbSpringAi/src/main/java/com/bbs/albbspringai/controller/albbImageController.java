package com.bbs.albbspringai.controller;

import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@RestController
public class albbImageController {

    @Autowired
    private DashScopeImageModel imageModel;

    @GetMapping("/albb/image")
    public void generateImage(
            @RequestParam(defaultValue = "生成一只小猫") String prompt,
            @RequestParam(defaultValue = "512x1024") String size,
            @RequestParam(defaultValue = "1") int n,
//            @RequestParam(defaultValue = "standard") String quality,
            HttpServletResponse response) {

        // 解析宽高
        String[] dimensions = size.split("x");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        // 配置图片选项
        ImageOptions imageOptions = ImageOptionsBuilder.builder()
                .width(width)
                .height(height)
                .N(n)
                .build() ;

        // 创建图片生成请求
        ImagePrompt imagePrompt = new ImagePrompt(prompt, imageOptions);

        // 调用模型
        ImageResponse imageResponse = imageModel.call(imagePrompt);

        // 检查结果
        if (imageResponse == null || imageResponse.getResult() == null ||
                imageResponse.getResult().getOutput() == null) {
            throw new RuntimeException("图像生成失败，请检查API Key或网络连接");
        }

        // 获取图片地址
        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        try {
            URL url = new URI(imageUrl).toURL();
            InputStream inputStream = url.openStream();
            response.setHeader("Content-Type", "image/png");
            response.getOutputStream().write(inputStream.readAllBytes());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException("图片下载失败", e);
        }
    }
}
package com.raiyee.easyhi.apis.merchant.api;


import com.github.bingoohuang.springrestclient.annotations.SpringRestClientEnabled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SpringRestClientEnabled(baseUrl = "http://127.0.0.1:7769")
@RequestMapping("/v1/upload")
public interface UploadApi {
    /**
     * 上传图片，并且按照给定尺寸进行切图，切图后，文件名为 100X100.xxxx.jpg.
     *
     * @param tid       租户ID.
     * @param imageFile 需要上传的文件.
     * @param path      业务路径，类似于cookie的path，用于区分业务， 例如:a/b.
     * @param sizes     需要切成哪些尺寸的图片，例如100X100,180X180.
     * @return 返回原始图片的新文件名，不包括尺寸部分，例如: xxxx.jpg.
     */
    @RequestMapping(value = "/upload-image", method = POST)
    String uploadImage(@RequestParam("tid") String tid,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       @RequestParam("path") String path,
                       @RequestParam("sizes") String sizes);

    /**
     * 上传图片转换为BASE64编码.
     * 编码后的文件后缀为.BASE64.
     *
     * @param tid    租户ID.
     * @param base64 需要上传的图片的BASE64形式，详见https://en.wikipedia.org/wiki/Data_URI_scheme.
     *               例如src属性的取值部分：
     *               <pre>
     *                       <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUA
     *                        AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO
     *                       9TXL0Y4OHwAAAABJRU5ErkJggg==" alt="Red dot" />
     *               </pre>
     * @param path   业务路径，类似于cookie的path，用于区分业务， 例如:a/b.
     * @return 返回原始图片的新文件名，不包括后缀部分.
     */
    @RequestMapping(value = "/upload-base64-as-image", method = POST)
    String uploadBase64AsImage(@RequestParam("tid") String tid,
                               @RequestParam("base64") String base64,
                               @RequestParam("path") String path);
}

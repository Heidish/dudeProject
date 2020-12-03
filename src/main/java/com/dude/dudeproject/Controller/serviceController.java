package com.dude.dudeproject.Controller;

import com.dude.dudeproject.System.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Controller
//@EnableCaching
//@EnableScheduling
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.DELETE})
public class serviceController {

    public static final String QRCODE_ENDPOINT = "/qrcode";
    public static final long THIRTY_MINUTES = 1800000;


    @Autowired
    ImageService imageService;


    @GetMapping(value = QRCODE_ENDPOINT, produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<ResponseEntity<byte[]>> getQRCode(/*@RequestParam(value = "site", required = true*/  String site ) {

            site="http://localhost:8090/";


        return imageService.generateQRCode(site, 350, 350).map
                (imageBuff ->
                        ResponseEntity.ok()
                                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                                .body(imageBuff)
                );


    }


//    @Scheduled(fixedRate = THIRTY_MINUTES)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping(value = QRCODE_ENDPOINT)
//    public void deleteAllCachedImages() {
//        imageService.purgeCache();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> indexRouter(@Value("") final Resource indexHtml) {
//        return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
//    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body(ex.getMessage());
    }


}

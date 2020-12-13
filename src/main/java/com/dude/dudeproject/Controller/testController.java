package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Repository.userRepository;
import com.dude.dudeproject.Service.userDaoService;
import com.sun.media.jfxmediaimpl.MediaUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class testController {

    private userDaoService service;
    private userRepository repository;

    @RequestMapping(value = "/get_barcode", method = RequestMethod.GET)
    public ResponseEntity<byte[]> get_partner_image(HttpServletRequest request, Model model)
            throws Exception {

    smsp.print_String("get_barcode ==>" + "아악." );

    String text ="https://localhost:8090";
    String _formatName = text;
    text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
    
    byte[] imageInByte_re = smsp.getBarCodeImage(text, 840, 160);

     smsp.print_String(image.toString());
     ttpHeaders headers = new HttpHeaders();String mt_filename = text+".png";
     String formatName = "png";
     MediaType mType = MediaUtils.getMediaType(formatName);
     smsp.print_String(mType.toString());
     if(mType != null){
      headers.setContentType(mType);
     } else { mt_filename = new String(mt_filename.getBytes("UTF-8"), "ISO-8859-1"); headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); headers.add("Content-Disposition", "attachment;filename=\""+mt_filename+"\";"); headers.add("Content-Transfer-Encoding", "binary"); } return new ResponseEntity<byte[]>(imageInByte_re, headers,HttpStatus.OK); }


}

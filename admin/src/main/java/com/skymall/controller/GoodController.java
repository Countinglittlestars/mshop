package com.skymall.controller;

import com.skymall.dto.GoodAddDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/admin/good")
public class GoodController {

    /**
     *
     * @param goodDto
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object addGood(@RequestBody GoodAddDto goodDto){

        return null;
    }


}

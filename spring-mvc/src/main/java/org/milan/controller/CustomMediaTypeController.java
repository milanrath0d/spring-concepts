package org.milan.controller;

import org.milan.model.Item;
import org.milan.model.ItemV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Custom media types useful while versioning the APIs
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/mediaTypes")
public class CustomMediaTypeController {

    @GetMapping(value = "/test/{id}", produces = "application/vnd.milan.api.v1+json")
    public Item getItem(@PathVariable String id) {
        return new Item(id);
    }

    @GetMapping(value = "/test/{id}", produces = "application/vnd.milan.api.v2+json")
    public ItemV2 getItemV2(@PathVariable String id) {
        return new ItemV2(id);
    }
}

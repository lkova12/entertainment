package com.entertainment.controller;

import com.entertainment.clients.ApiUtils;
import com.entertainment.domain.Item;
import com.entertainment.service.LookupService;
import com.entertainment.util.WebUtils;
import feign.FeignException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final List<LookupService> lookupServices;

    @GetMapping("/")
    public String main(Model model) {
        return WebUtils.INDEX_PAGE;
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "query") String query, Model model) {
        try {
            List<Item> result = new ArrayList<>();
            lookupServices.stream().map(lookupService -> lookupService.findByQuery(query)).forEach(result::addAll);
            result.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
            model.addAttribute(WebUtils.ITEMS_ATTR, result);
            return WebUtils.INDEX_PAGE;
        } catch (final FeignException ex) {
            return ApiUtils.createErrorResponse(ex, model);
        }
    }
}

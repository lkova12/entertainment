package com.entertainment.clients;

import com.entertainment.util.WebUtils;
import feign.FeignException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

@Slf4j
@UtilityClass
public class ApiUtils {

    public static String createErrorResponse(final FeignException ex, Model model) {
        log.error("Error on request: ", ex);
        model.addAttribute(WebUtils.ERROR_MESSAGE_ATTR, ex.getMessage());
        return WebUtils.ERROR_PAGE;
    }
}

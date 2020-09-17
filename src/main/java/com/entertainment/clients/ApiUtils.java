package com.entertainment.clients;

import com.entertainment.util.WebUtils;
import feign.FeignException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

/**
 * Utility class for working with connections to other APIs.
 */
@Slf4j
@UtilityClass
public class ApiUtils {

    public static final String EMPTY = "";

    /**
     * Log exception and redirect to an error page with an error message
     * if something went wrong with calling other APIs.
     *
     * @param ex
     * @param model
     * @return the name of view
     */
    public static String createErrorResponse(final FeignException ex, Model model) {
        log.error("Error on request: ", ex);
        model.addAttribute(WebUtils.ERROR_MESSAGE_ATTR, ex.getMessage());
        return WebUtils.ERROR_PAGE;
    }
}

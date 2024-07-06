package org.team.springboot.configure;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zhangds
 * @date 2024/7/6
 * @Notes
 **/

@ComponentScan
@Configuration
@RestController
public class WebJarConfig {
    private final WebJarAssetLocator assetLocator = new WebJarAssetLocator();

    @ApiIgnore
    @ResponseBody
    @RequestMapping(value="/webjarslocator/{webjar}/**",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<Object> locateWebjarAsset(@PathVariable String webjar, HttpServletRequest request) {
        try {
            String mvcPrefix = "/webjarslocator/" + webjar + "/";
            String mvcPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String fullPath = assetLocator.getFullPath(webjar, mvcPath.substring(mvcPrefix.length()));
            return new ResponseEntity<Object>(new ClassPathResource(fullPath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }
}

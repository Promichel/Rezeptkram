package org.springframework.security.taglibs.facelets;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagHandler;
import java.io.IOException;

/**
 * Taglib to combine the Spring-Security Project with Facelets <br />
 * <p/>
 * This is the Class responsible for making the <br />
 * <code><br />
 * &lt;sec:isAuthenticated;&gt;<br />
 * The components you want to show only when the user is authenticated<br />
 * lt;/sec:isAuthenticated&gt;<br />
 * </code>
 * work.
 *
 * @author Grzegorz Blaszczyk - http://www.blaszczyk-consulting.com/
 * @version %I%, %G%
 * @since 0.5
 */
public class IsAuthenticatedTag extends TagHandler {

    public void apply(FaceletContext faceletContext, UIComponent uiComponent)
            throws IOException, FacesException, FaceletException, ELException {

        if (SpringSecurityELLibrary.isAuthenticated()) {
            this.nextHandler.apply(faceletContext, uiComponent);
        }
    }

    public IsAuthenticatedTag(ComponentConfig componentConfig) {
        super(componentConfig);
    }

}
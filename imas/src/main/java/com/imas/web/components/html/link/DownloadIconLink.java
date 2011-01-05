package com.imas.web.components.html.link;

import java.io.File;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class DownloadIconLink extends Panel {

    private static final long serialVersionUID = 1L;
    private static final ResourceReference DOWNLOAD_ICON_RES = new ResourceReference(DownloadIconLink.class, "img/download_icon_small.png");
    
    public DownloadIconLink(String id, File file, String iconImgSrc) {
        super(id);
        DownloadLink downloadLink = new DownloadLink("download", file) {
            private static final long serialVersionUID = 1L;

            @Override public void onClick() {
                doBeforeClick();
                super.onClick();
            }            
        };
        downloadLink.add(new Image("icon", DOWNLOAD_ICON_RES));
        add(downloadLink);
    }
    
    public DownloadIconLink(String id, File file, String iconImgSrc, IModel<String> title) {
        this(id, file, iconImgSrc);
        add(new AttributeModifier("title", true, title));
    }

    public DownloadIconLink(String id, File file) {
        super(id);
        DownloadLink downloadLink = new DownloadLink("download", file);
        downloadLink.add(new Image("icon", DOWNLOAD_ICON_RES));
        add(downloadLink);
    }
    
    public DownloadIconLink(String id, File file, IModel<String> title) {
        this(id, file);
        add(new AttributeModifier("title", true, title));
    }
    
    /**
     * A method to hook up methods before calling onClick.
     * Mainly used to generate the PDF on demand when the download icon is clicked.
     */
    public abstract void doBeforeClick();
    
}

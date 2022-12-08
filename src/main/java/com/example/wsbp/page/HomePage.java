package com.example.wsbp.page;

import com.example.wsbp.service.ISampleService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    @SpringBean
    private ISampleService service;

    public HomePage() {
        var youModel = Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you", youModel);
        add(youLabel);

        var gakusekiModel = Model.of("b2200090");
        var gakusekiLabel = new Label("gakuseki", gakusekiModel);
        add(gakusekiLabel);

        var nameModel = Model.of("Akiyama Kenta");
        Label nameLabel = new Label("name", nameModel);
        add(nameLabel);

        var timeModel = Model.of(service.makeCurrentHMS());
        Label timeLabel = new Label("time", timeModel);
        add(timeLabel);

        var randModel = Model.of(service.makeRandInt());
        Label randLabel = new Label("rand", randModel);
        add(randLabel);

        var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
        add(toUserMakerLink);
    }

}
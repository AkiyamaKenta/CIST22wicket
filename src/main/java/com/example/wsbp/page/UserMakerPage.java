package com.example.wsbp.page;

//ユーザー追加01
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;

//ユーザー追加02
import org.apache.wicket.model.Model;

//テーブルにユーザー情報を記憶
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.wsbp.service.IUserService;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MountPath("UserMaker")
public class UserMakerPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public UserMakerPage() {
        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        var userInfoForm = new Form<>("userInfo") {
            @Override
            protected void onSubmit() {
                String userName = userNameModel.getObject();
                String userPass = userPassModel.getObject();
                String msg = "送信データ："
                        + userName
                        + ","
                        + userPass;
                System.out.println(msg);

                //ユーザー追加03
                userService.registerUser(userName, userPass);
                setResponsePage(new UserMakerCompPage(userNameModel));

//                Pattern p = Pattern.compile("^[0-9]*$");
//                Matcher m = p.matcher(userPass);
//                if (m.find()) {
//                    error("数字だけのパスワードはダメ");
//                    return;
//                }


            }
        };
        add(userInfoForm);

        var fbMsgPanel = new FeedbackPanel("fbMsg");
        userInfoForm.add(fbMsgPanel);

        var userNameField = new TextField<>("userName", userNameModel) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                add(StringValidator.lengthBetween(8, 32));
            }
        };

        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass", userPassModel) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                add(StringValidator.lengthBetween(8, 32));
            }
        };
        userInfoForm.add(userPassField);
    }

}
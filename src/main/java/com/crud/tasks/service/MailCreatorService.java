package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskService service;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Thank you for using Crud App!");
        context.setVariable("info_company_name", adminConfig.getInfoCompanyName());
        context.setVariable("info_company_goal", adminConfig.getInfoCompanyGoal());
        context.setVariable("info_company_email", adminConfig.getInfoCompanyEmail());
        context.setVariable("info_company_phone", adminConfig.getInfoCompanyPhone());
        context.setVariable("separator", " ");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyTasksReportEmail(String message) {
        List<Task> taskList = service.getAllTasks();
        int size = service.getAllTasks().size();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("goodbye_message", "Thank you for using Crud App!");
        context.setVariable("info_company_name", adminConfig.getInfoCompanyName());
        context.setVariable("info_company_goal", adminConfig.getInfoCompanyGoal());
        context.setVariable("info_company_email", adminConfig.getInfoCompanyEmail());
        context.setVariable("info_company_phone", adminConfig.getInfoCompanyPhone());
        context.setVariable("separator", ", ");
        context.setVariable("is_friend", false);
        context.setVariable("howManyTasks", size);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("tasks_list", taskList);
        return templateEngine.process("mail/daily-tasks-report-mail", context);
    }
}
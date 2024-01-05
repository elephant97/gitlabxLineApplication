package com.githooknotifyline.gitlabxlineapplication.handler;


import com.githooknotifyline.gitlabxlineapplication.handler.Adapter.GitLabEventAdapter;
import com.githooknotifyline.gitlabxlineapplication.model.GitLabEvent;
import com.githooknotifyline.gitlabxlineapplication.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IssueEventHandler implements GitLabEventAdapter {
    @Override
    public String getEventType() {
        return "Issue Hook";
    }

    @Override
    public ResponseEntity<?> handleEvent(String lineToken, GitLabEvent data, NotifyService notifyService) {
        log.debug("Event={}","Issue");
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),data.getAfter());
        return notifyService.issueNotify(lineToken, data);
    }
}

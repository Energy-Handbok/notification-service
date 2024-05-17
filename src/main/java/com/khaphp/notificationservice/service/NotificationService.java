package com.khaphp.notificationservice.service;


import com.khaphp.common.dto.ResponseObject;
import com.khaphp.notificationservice.dto.NotificationDTOcreate;
import com.khaphp.notificationservice.dto.NotificationDTOupdate;

public interface NotificationService {

    ResponseObject<Object> getAll(int pageSize, int pageIndex, String userId);
    ResponseObject<Object> getDetail(String id);
    ResponseObject<Object> create(NotificationDTOcreate object);
    ResponseObject<Object> update(NotificationDTOupdate object);
    ResponseObject<Object> updateSeen(String NotIid);
    ResponseObject<Object> delete(String id);
}

package com.khaphp.notificationservice.service.kafka;

import com.khaphp.common.dto.ResponseObject;
import com.khaphp.common.dto.notification.NotificationDTOcreate;
import com.khaphp.notificationservice.config.constant.KafkaConfigHelper;
import com.khaphp.notificationservice.config.constant.KafkaTopicHelper;
import com.khaphp.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;

    @KafkaListener(topics = KafkaTopicHelper.NOTIFY_USER_BIRTHDAY, groupId = KafkaConfigHelper.KAFKA_GROUP, containerFactory = "objectListener")   //vì lắng nghe object gửi về nên phải có containerFactory, objectListener là method mà ta config để lắng nghe ấy (bên class config có ghi á)
    public void createNotiBirthdayUser(NotificationDTOcreate object){
        log.info("rollback [createNotiBirthdayUser] consume the message json : {}", object.toString());
        try{
            //vì NotificationDTOcreate mà ta nhận từ USer Service có đường dẫn khác, mà method create(...) lại nhận object NotificationDTOcreate nhưng vs đường dẫn khác, nên ta phải map object từ kafka về sang object mà method nó nhận
            com.khaphp.notificationservice.dto.NotificationDTOcreate mapObject = modelMapper.map(object, com.khaphp.notificationservice.dto.NotificationDTOcreate.class);
            ResponseObject rs = notificationService.create(mapObject);
            if(rs.getCode() != 200){
                throw new RuntimeException("Noti sinh nhật cho user với id "+ object.getUserId()  +": " + rs.getMessage());
            }
            log.info("rollback {} successfully", mapObject.toString());
        }catch (Exception e){
            log.error("rollback {} failed with error {}", object.toString(), e.getMessage());
        }
    }
}

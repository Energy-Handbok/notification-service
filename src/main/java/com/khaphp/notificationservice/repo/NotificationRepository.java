package com.khaphp.notificationservice.repo;

import com.khaphp.notificationservice.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    Page<Notification> findByUserId(String userId, PageRequest pageRequest);
}

package com.example.repositories;

import com.example.models.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {

    List<NotificationModel> findByType(String type);
    List<NotificationModel> findByTypeAndAlwaysTrue(String type);

    @Query(value = """
                SELECT n.* FROM tc_notifications n
                INNER JOIN tc_user_notification u ON n.id = u.notificationid
                WHERE u.userid = :userId
            """, nativeQuery = true)
    List<NotificationModel> findByUserId(@Param("userId") Long userId);

    @Query(value = """
                SELECT COUNT(*) FROM tc_user_notification
                WHERE userid = :userId AND notificationid = :notificationId
            """, nativeQuery = true)
    Integer countByUserAndNotification(@Param("userId") Long userId, @Param("notificationId") Long notificationId);

    @Query(value = """
                SELECT EXISTS (
                    SELECT 1 FROM tc_user_notification
                    WHERE userid = :userId AND notificationid = :notificationId
                )
            """, nativeQuery = true)
    int isLinkedToUser(@Param("userId") Long userId, @Param("notificationId") Long notificationId);

}
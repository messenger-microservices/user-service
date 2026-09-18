package ru.pulsarmn.messenger.user.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.pulsarmn.messenger.user.domain.User;

import java.util.UUID;


public interface UserRepository extends JpaRepository<@NonNull User, @NonNull UUID> {

    @Query("SELECT u FROM User u WHERE u.username ILIKE CONCAT('%', :username, '%')")
    Page<@NonNull User> searchUsers(@Param("username") String username, Pageable pageable);
}

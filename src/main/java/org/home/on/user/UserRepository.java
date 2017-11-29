package org.home.on.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<UserEntity>findByName(String name);



}

package com.example.websocketapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository <Message, Integer>{
}

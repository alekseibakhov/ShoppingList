package com.example.demo.service;

import com.concretepage.gs_ws.PostRequest;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingService {
    void processRequest(PostRequest request);
}

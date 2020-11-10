package top.wy.tool.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wdz
 * @date :
 */
@Service
public interface DatabaseService {
    public List<String> getAllTables();
}

package top.wy.tool.service;

import org.springframework.stereotype.Service;
import top.wy.tool.entity.TableStructure;

import java.util.List;

/**
 * @author : wdz
 * @date :
 */
@Service
public interface TableService {
    public List<TableStructure> getStructure();
}

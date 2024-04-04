package com.matithemati.cm.service;

import com.matithemati.cm.model.CosmeticService;
import com.matithemati.cm.repository.CosmeticServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CosmeticServiceService {
    private final CosmeticServiceRepository cosmeticServiceRepository;

    public void addCosmeticService(CosmeticService cosmeticService) {
        cosmeticServiceRepository.save(cosmeticService);
    }
}

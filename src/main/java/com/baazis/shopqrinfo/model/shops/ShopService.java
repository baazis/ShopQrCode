package com.baazis.shopqrinfo.model.shops;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<Shops> getStudents(){
        return shopRepository.findAll();
    }
    public Shops addShop(Shops shops){
        if (shops.getId() != null && existsById(shops.getId())) {
            return updateShop(shops);
        } else {
            return createShop(shops);
        }
    }
    private Shops createShop(Shops shops) {
        Long newId = getNextAvailableId();
        shops.setId(newId);
        return shopRepository.save(shops);
    }

    private Shops updateShop(Shops shops) {
        Shops existingShop = findById(shops.getId());
        existingShop.setFirstName(shops.getFirstName());
        existingShop.setLastName(shops.getLastName());
        existingShop.setLink(shops.getLink());
        existingShop.setMobile(shops.getMobile());
        existingShop.setEmail(shops.getEmail());
        return shopRepository.save(existingShop);
    }

    public Shops deleteShop(Long id){
        Shops shop = findById(id); // Find the shop by ID before deleting
        shopRepository.deleteById(id);
        return shop;
    }
    public boolean existsById(Long id){
//        Shops existingShop = findById(id);
        return shopRepository.existsById(id);
    }

    public Shops findById(Long id){
        return shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    private Long getNextAvailableId() {
        List<Shops> allShops = shopRepository.findAll();
        if (allShops.isEmpty()) {
            return 1L; // Start from ID 1 if there are no existing shops
        } else {
            return allShops.size() + 1L; // Generate the next available ID
        }
    }





}

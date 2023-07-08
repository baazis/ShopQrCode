package com.baazis.shopqrinfo.model.shops;

import com.baazis.shopqrinfo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<List<Shops>> getShops() throws IOException, WriterException {
        List<Shops> shops = shopService.getStudents();
        if (shops.size() != 0){
            for (Shops shop : shops){
                QRCodeGenerator.generateQRCode(shop);
            }
        }
        return ResponseEntity.ok(shopService.getStudents());
    }

    @PostMapping
    public Shops addShop(@RequestBody Shops shops){
        return shopService.addShop(shops);
    }

    @GetMapping("/{id}")
    public Shops findById(@PathVariable("id") Long id){
        return shopService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateShop(@PathVariable("id") Long id, @RequestBody Shops shops){
        if(!shopService.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not found");
        }
        shops.setId(id);
        shopService.addShop(shops);
    }
    @DeleteMapping("/{id}")
    public Shops deleteShop(@PathVariable("id") Long id){
        return shopService.deleteShop(id);
    }
}

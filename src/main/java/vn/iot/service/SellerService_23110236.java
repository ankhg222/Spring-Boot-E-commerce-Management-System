package vn.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iot.model.Seller_23110236;
import vn.iot.repository.SellerRepository_23110236;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService_23110236 {
    
    @Autowired
    private SellerRepository_23110236 sellerRepository;
    
    public List<Seller_23110236> getAllSellers() {
        return sellerRepository.findAll();
    }
    
    public Optional<Seller_23110236> getSellerById(Integer id) {
        return sellerRepository.findById(id);
    }
    
    public Seller_23110236 saveSeller(Seller_23110236 seller) {
        return sellerRepository.save(seller);
    }
    
    public void deleteSeller(Integer id) {
        sellerRepository.deleteById(id);
    }
}

package proyecto.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.proyecto.models.Product;
import proyecto.proyecto.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService cS;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        try
        {
            List<Product> prods = cS.getAll();
            if(prods.size()==0){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Esta vacio, ves? no hay tickets aqui. Buena, ahora si hay uno pero no voy a asustarte. No es mi zona :)");
            }
            return ResponseEntity.status(200).body(prods);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }
    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Product prod)
    {
        try
        {
            Product c = cS.save(prod);
            return ResponseEntity.status(200).body(c);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> delete(@PathVariable("code") long code)
    {   
        try {
            boolean f = cS.delete(code);
            if(f)
            {
                return ResponseEntity.status(200).body("Success.");
            }
            else
            {
                return ResponseEntity.status(204).body("Product Not found.");
            }
        } catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
        
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<Object> update(@PathVariable("code") long code, @RequestBody Product prod)
    {
        Product c = cS.update(prod, code);
        if(c != null)
        {
            return ResponseEntity.status(200).body("Success");
        }
        else {
            return ResponseEntity.status(204).body("Client Not Found");
        }
    }
}

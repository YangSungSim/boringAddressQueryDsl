package com.example.lazyaddressdsl.controller;

import com.example.lazyaddressdsl.entity.Address;
import com.example.lazyaddressdsl.entity.AddressForm;
import com.example.lazyaddressdsl.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping("/address/new")
    public String createForm(Model model) {
        HashMap<String, String> ErrorHash = new HashMap<String, String>();

        model.addAttribute("addressForm", new AddressForm());
        model.addAttribute("update", "false");
        model.addAttribute("field", ErrorHash);
        return "address/createAddressForm";
    }

    @PostMapping("/address/new")
    public String create(@Valid AddressForm form
            , BindingResult result
            , Model model) {
        Address address = new Address(form.getName(), form.getAge(), form.getPhone());

        if (result.hasErrors()) {
            int errorCnt = result.getErrorCount();
            List<ObjectError> allErrors = result.getAllErrors();
            HashMap<String, String> ErrorHash = new HashMap<String, String>();
            for (int errcnt = 0; errcnt< errorCnt; errcnt++) {
                String typeClass = allErrors.get(errcnt).getCodes()[1];
                int jumIndex = typeClass.indexOf(".");
                String errorField = typeClass.substring(jumIndex+1, typeClass.length());
                String errorMsg = allErrors.get(errcnt).getDefaultMessage();
                ErrorHash.put(errorField,errorMsg);
            }

            model.addAttribute("addressForm", address);
            model.addAttribute("update", "false");
            model.addAttribute("field", ErrorHash);
            return "address/createAddressForm";
        }
        addressRepository.save(address);
        return "redirect:/address";
    }

    @PostMapping("/address/{id}/update")
    public String updateForm(@PathVariable("id") Long id,
                             Model model) {

        Address address = addressRepository.findOne(id);
        AddressForm newAddressForm = new AddressForm(address.getId()
                , address.getName()
                , address.getAge()
                , address.getPhone());

        HashMap<String, String> ErrorHash = new HashMap<String, String>();

        model.addAttribute("addressForm", newAddressForm);
        model.addAttribute("update", "true");
        model.addAttribute("field", ErrorHash);
        return "address/createAddressForm";
    }

    @PostMapping("/address/update")
    public String update(@Valid AddressForm form
            , BindingResult result
            , Model model) {

        Address address = new Address(form.getId(), form.getName(), form.getAge(), form.getPhone());

        if (result.hasErrors()) {
            int errorCnt2 = result.getErrorCount();
            List<ObjectError> allErrors = result.getAllErrors();
            HashMap<String, String> ErrorHash2 = new HashMap<String, String>();
            for (int errCnt2 = 0; errCnt2< errorCnt2; errCnt2++) {
                String typeClass = allErrors.get(errCnt2).getCodes()[1];
                int jumIndex = typeClass.indexOf(".");
                String errorField = typeClass.substring(jumIndex+1, typeClass.length());
                String errorMsg = allErrors.get(errCnt2).getDefaultMessage();
                ErrorHash2.put(errorField,errorMsg);
            }

            model.addAttribute("addressForm", address);
            model.addAttribute("update", "true");
            model.addAttribute("field", ErrorHash2);
            return "address/createAddressForm";
        }

        addressRepository.update(address);
        return "redirect:/address";
    }




    @GetMapping("/address")
    public String list(Model model) {
        System.out.println("한글나와라");
        List<Address> addresses = addressRepository.findAddress();
        model.addAttribute("addresses", addresses);
        return "address/addressList";
    }

    @GetMapping(value="/address/friend")
    public String findOne(@RequestParam String name,
                          Model model){
        String friendName = String.valueOf(name);

        if (friendName.trim().length() >= 1) {
            Address addressOne = addressRepository.findByName(friendName);
            model.addAttribute("addresses", addressOne);
            return "address/addressList";

        } else {
            return "redirect:/address";
        }

    }

    @PostMapping(value="/address/{addressId}/delete")
    public String deleteAddress(@PathVariable("addressId") Long addressId) {
        addressRepository.deleteAddress(addressId);
        return "redirect:/address";
    }
}

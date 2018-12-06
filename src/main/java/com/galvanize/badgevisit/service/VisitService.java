package com.galvanize.badgevisit.service;

import com.galvanize.badgevisit.entity.Visit;
import com.galvanize.badgevisit.entity.Visitor;
import com.galvanize.badgevisit.entity.VisitorExtended;
import com.galvanize.badgevisit.entity.VisitorFrontEnd;
import com.galvanize.badgevisit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    @Autowired
    VisitRepository visitRepository;

    public VisitorFrontEnd createVisit(VisitorFrontEnd visitor) {
        Visit savedVisit = visitRepository.save(visitFromVisitorFrontEnd(visitor));
        return visitorFrontEndFromVisit(savedVisit);
    }


    Visit visitFromVisitorFrontEnd(VisitorFrontEnd v) {
        return Visit.builder()
                .phoneNumber(phoneNumberFromString(v.getPhoneNumber()))
                .hostName(v.getHostName())
                .hostPhoneNumber(phoneNumberFromString(v.getHostPhone()))
                .purposeOfVisit(v.getPurposeOfVisit())
                .checkedInBy(v.getCheckedInBy())
                .checkedOutBy(v.getCheckedOutBy())
                .reasonForDeletion(v.getReasonForDeletion())
                .badgeNumber(v.getBadgeNumber())
                .registerDate(v.getRegisterDate())
                .checkedInDate(v.getCheckedInDate())
                .checkedOutDate(v.getCheckedOutDate())
                .active(v.getActive())
                .status(v.getStatus())
                .build();

    }

    VisitorFrontEnd visitorFrontEndFromVisit(Visit v) {
        return VisitorFrontEnd.builder()
                .phoneNumber(phoneNumberStringFormat(v.getPhoneNumber()))
                .hostName(v.getHostName())
                .hostPhone(phoneNumberStringFormat(v.getHostPhoneNumber()))
                .purposeOfVisit(v.getPurposeOfVisit())
                .checkedInBy(v.getCheckedInBy())
                .checkedOutBy(v.getCheckedOutBy())
                .reasonForDeletion(v.getReasonForDeletion())
                .badgeNumber(v.getBadgeNumber())
                .registerDate(v.getRegisterDate())
                .checkedInDate(v.getCheckedInDate())
                .checkedOutDate(v.getCheckedOutDate())
                .active(v.getActive())
                .status(v.getStatus())
                .build();
    }






    Visitor visitorFromVisitorFrontEnd(VisitorFrontEnd visitorFrontEnd) {
        return Visitor.builder()
                .phoneNumber(phoneNumberFromString(visitorFrontEnd.getPhoneNumber()))
                .firstName(visitorFrontEnd.getFirstName())
                .lastName(visitorFrontEnd.getLastName())
                .company(visitorFrontEnd.getCompany())
                .build();
    }

    VisitorExtended visitorExtendedFromVisitorFrontEnd(VisitorFrontEnd visitorFrontEnd) {
        return VisitorExtended.builder()
                .phoneNumber(phoneNumberFromString(visitorFrontEnd.getPhoneNumber()))
                .firstName(visitorFrontEnd.getFirstName())
                .lastName(visitorFrontEnd.getLastName())
                .company(visitorFrontEnd.getCompany())
                .hostName(visitorFrontEnd.getHostName())
                .hostPhone(phoneNumberFromString(visitorFrontEnd.getHostPhone()))
                .purposeOfVisit(visitorFrontEnd.getPurposeOfVisit())
                .build();
    }

    private Long phoneNumberFromString(String phoneNumber) {
        try {
            return Long.parseLong(phoneNumber.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    private String phoneNumberStringFormat(Long number) {
        if (number == null || number == 0)
            return "";
        String phone = number.toString();
        try {
            return String.format("(%s)%s-%s",
                    phone.substring(0, 3),
                    phone.substring(3, 6),
                    phone.substring(6));
        } catch (Exception e) {
            return phone;
        }
    }

    public Iterable<Visit> getAll() {
        return visitRepository.findAll();
    }
}

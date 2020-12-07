package com.springframework.springbootpetclinic.converters;

import com.springframework.springbootpetclinic.commands.OwnersCommand;
import com.springframework.springbootpetclinic.model.Owner;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OwnersCommandToOwners implements Converter<OwnersCommand, Owner> {

    @Synchronized
    @Nullable
    @Override
    public Owner convert(OwnersCommand source) {
        if(source == null)
            return null;
        final Owner owner = Owner.ownerBuilder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .build();
        return owner;
    }
}

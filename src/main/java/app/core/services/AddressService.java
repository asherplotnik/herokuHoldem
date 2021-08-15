package app.core.services;

import app.core.entities.Player;
import app.core.exceptions.HoldemException;
import app.core.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    @Autowired
    PlayerRepository playerRepository;

    public boolean approveIpAddress(String ipAddress, int id) throws HoldemException {
        try{
            List<Player> players = playerRepository.findByIpAddress(ipAddress);
            for (Player player: players){
                if (id != player.getId()){
                    System.out.println("found ip"+player.getId());
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }
}

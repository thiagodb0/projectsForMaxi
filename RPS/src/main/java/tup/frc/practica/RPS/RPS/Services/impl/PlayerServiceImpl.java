package tup.frc.practica.RPS.RPS.Services.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tup.frc.practica.RPS.RPS.Entities.PlayerEntity;
import tup.frc.practica.RPS.RPS.Models.Player;
import tup.frc.practica.RPS.RPS.Repositories.PlayerJpaRepository;
import tup.frc.practica.RPS.RPS.Services.PlayerService;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Player getPlayerById(Long id) {
        Optional<PlayerEntity> playerEntity = playerJpaRepository.findById(id);
        if(playerEntity.isPresent()){
            return modelMapper.map(playerEntity, Player.class);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Player savePlayer(Player player) {
        Optional<PlayerEntity> playerEntityOption = playerJpaRepository.findByUserNameOrEmail(player.getUserName(), player.getEmail());
        if(playerEntityOption.isPresent()){
            throw new EntityExistsException("The username or email already Exists");
        }
        PlayerEntity playerEntity = playerJpaRepository.save(modelMapper.map(player, PlayerEntity.class));
        if(Objects.isNull(playerEntity)){
            throw new RuntimeException("Error saving the player");
        }
        else
            return modelMapper.map(playerEntity, Player.class);
    }


//    Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmail(
//            newPlayerRequestDTO.getUserName(), newPlayerRequestDTO.getEmail());
//        if(playerEntityOptional.isEmpty()){
//        PlayerEntity playerEntity = modelMapper.map(newPlayerRequestDTO, PlayerEntity.class);
//        playerEntity.setBalance(BigDecimal.valueOf(200));
//        PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity);
//        return modelMapper.map(playerEntitySaved, PlayerResponseDTO.class);
//    }
//        else
//    {
//        throw new EntityExistsException("Player user name or email already exists");
//    }


}

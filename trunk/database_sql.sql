# Factory table
CREATE  TABLE IF NOT EXISTS `factory`.`generator` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `modbus_address` TINYINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `modbus_address_UNIQUE` (`modbus_address` ASC) )
ENGINE = InnoDB;

# Factory data table
CREATE  TABLE IF NOT EXISTS `factory`.`generator_data` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `generator_id` INT NOT NULL ,
  `G_Ua` SMALLINT NOT NULL ,
  `G_Ub` SMALLINT NOT NULL ,
  `G_Uc` SMALLINT NOT NULL ,
  `G_Uab` SMALLINT NOT NULL ,
  `G_Ubc` SMALLINT NOT NULL ,
  `G_Uca` SMALLINT NOT NULL ,
  `L_Ua` SMALLINT NOT NULL ,
  `L_Ub` SMALLINT NOT NULL ,
  `L_Uc` SMALLINT NOT NULL ,
  `L_Uab` SMALLINT NOT NULL ,
  `L_Ubc` SMALLINT NOT NULL ,
  `L_Uca` SMALLINT NOT NULL ,
  `Ia` SMALLINT NOT NULL ,
  `Ib` SMALLINT NOT NULL ,
  `Ic` SMALLINT NOT NULL ,
  `Pa` SMALLINT NOT NULL ,
  `Pb` SMALLINT NOT NULL ,
  `Pc` SMALLINT NOT NULL ,
  `Qa` SMALLINT NOT NULL ,
  `Qb` SMALLINT NOT NULL ,
  `Qc` SMALLINT NOT NULL ,
  `Sa` SMALLINT NOT NULL ,
  `Sb` SMALLINT NOT NULL ,
  `Sc` SMALLINT NOT NULL ,
  `PFa` SMALLINT NOT NULL ,
  `PFb` SMALLINT NOT NULL ,
  `PFc` SMALLINT NOT NULL ,
  `P3Phases` SMALLINT NOT NULL ,
  `Q3Phases` SMALLINT NOT NULL ,
  `S3Phases` SMALLINT NOT NULL ,
  `PF3Phases` SMALLINT NOT NULL ,
  `G_RealFreq` SMALLINT NOT NULL ,
  `L_RealFreq` SMALLINT NOT NULL ,
  `I0` SMALLINT NOT NULL ,
  `PowerEnergy` SMALLINT NOT NULL ,
  `SpeedGuardStatus` SMALLINT NOT NULL ,
  `RPM` SMALLINT NOT NULL ,
  `Temperature1` SMALLINT NOT NULL ,
  `Temperature2` SMALLINT NOT NULL ,
  `Temperature3` SMALLINT NOT NULL ,
  `Temperature4` SMALLINT NOT NULL ,
  `Temperature5` SMALLINT NOT NULL ,
  `Temperature6` SMALLINT NOT NULL ,
  `Temperature7` SMALLINT NOT NULL ,
  `Temperature8` SMALLINT NOT NULL ,
  `Temperature9` SMALLINT NOT NULL ,
  `Temperature10` SMALLINT NOT NULL ,
  `Temperature11` SMALLINT NOT NULL ,
  `Temperature12` SMALLINT NOT NULL ,
  `Press1` SMALLINT NOT NULL ,
  `Press2` SMALLINT NOT NULL ,
  `Press3` SMALLINT NOT NULL ,
  `Press4` SMALLINT NOT NULL ,
  `ContactGroup1` SMALLINT NOT NULL ,
  `ContactGroup2` SMALLINT NOT NULL ,
  `ContactGroup3` SMALLINT NOT NULL ,
  `ContactGroup4` SMALLINT NOT NULL ,
  `WaterOpenAngle` SMALLINT NOT NULL ,
  `FuelLevel1` SMALLINT NOT NULL ,
  `FuelLevel2` SMALLINT NOT NULL ,
  `ValvePlace` SMALLINT NOT NULL ,
  `TimeStamp` TIMESTAMP NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `generator_fk_idx` (`generator_id` ASC) ,
  CONSTRAINT `generator_fk`
    FOREIGN KEY (`generator_id` )
    REFERENCES `factory`.`generator` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

# Test datagenerator_data
INSERT INTO `factory`.`generator` (`modbus_address`) VALUES ('10');
INSERT INTO `factory`.`generator` (`modbus_address`) VALUES ('11');
INSERT INTO `factory`.`generator` (`modbus_address`) VALUES ('12');

INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('1', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '55', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52', '54', '55', '56', '57', '58', '59', '60', '61', '62');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('2', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('3', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('1', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('2', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('3', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('1', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('2', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');
INSERT INTO `factory`.`generator_data` (`generator_id`, `G_Ua`, `G_Ub`, `G_Uc`, `G_Uab`, `G_Ubc`, `G_Uca`, `L_Ua`, `L_Ub`, `L_Uc`, `L_Uab`, `L_Ubc`, `L_Uca`, `Ia`, `Ib`, `Ic`, `Pa`, `Pb`, `Pc`, `Qa`, `Qb`, `Qc`, `Sa`, `Sb`, `Sc`, `PFa`, `PFb`, `PFc`, `P3Phases`, `Q3Phases`, `S3Phases`, `PF3Phases`, `G_RealFreq`, `L_RealFreq`, `I0`, `PowerEnergy`, `SpeedGuardStatus`, `RPM`, `Temperature1`, `Temperature2`, `Temperature3`, `Temperature4`, `Temperature5`, `Temperature6`, `Temperature7`, `Temperature8`, `Temperature9`, `Temperature10`, `Temperature11`, `Temperature12`, `Press1`, `Press2`, `Press3`, `Press4`, `ContactGroup1`, `ContactGroup2`, `ContactGroup3`, `ContactGroup4`, `WaterOpenAngle`, `FuelLevel1`, `FuelLevel2`, `ValvePlace`) VALUES ('3', '11', '22', '33', '44', '55', '66', '77', '88', '99', '00', '21', '2312', '34', '234', '435', '54', '23', '43', '12', '43', '54', '65', '76', '34', '45', '345', '34', '54', '65', '543', '234', '54', '65', '43', '54', '65', '76', '87', '98', '34', '23', '54', '65', '76', '34', '65', '76', '43', '23', '43', '43', '54', '65', '76', '54', '32', '43', '54', '35', '345', '453');

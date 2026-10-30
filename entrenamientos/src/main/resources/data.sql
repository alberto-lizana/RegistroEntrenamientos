INSERT INTO tipo_rutina(nombre, dias_por_semana, dias_descanso, descripcion) 
    VALUES 
        ('PUSH, PULL, LEGS', 6, 1, 'Tipo de rutina que se enfoca en dividir los entrenamientos en 6 dias uno de empujes, otro de tirones y otro de piernas.'),
        ('PPL DENSITY', 5, 2,'Tipo de rutina de 5 días que divide los entrenamientos en Push, Pull, Legs, Push y Density. La sesión Density combina espalda y piernas, priorizando ejercicios de pierna que también involucran la musculatura de la espalda.'),
        ('TORSO/PIERNA', 4, 3, 'Tipo de rutina que se enfoca en dividir los entrenamientos en dos partes: parte superior e inferior del cuerpo.'),
        ('FULL BODY', 3, 4, 'Tipo de rutina que se enfoca en trabajar todos los grupos musculares en una sola sesión.');

INSERT INTO rutina(titulo, tipo_rutina_id)
    VALUES 
        ('Rutina Avanzada', 1),
        ('Rutina Intensiva', 2),
        ('Rutina Equilibrada', 3),
        ('Rutina Ejecutiva', 4);

INSERT INTO ejercicio(nombre)
    VALUES 
        /* PECHO */                                                    
        ('Press de Banca con Barra'),                                  /*1*/                                                                
        ('Press de Banca con Mancuernas'),                             /*2*/                                                                     
        ('Press de Banca con Máquina'),                                /*3*/                                                                                            
        ('Press de Banca Inclinado con Barra'),                        /*4*/                                                                          
        ('Press de Banca Inclinado con Mancuernas'),                   /*5*/                                                                               
        ('Press de Banca Inclinado con Máquina'),                      /*6*/                                                                                                    
        ('Press de Banca Declinado con Barra'),                        /*7*/                                                                          
        ('Press de Banca Declinado con Mancuernas'),                   /*8*/                                                                                                   
        ('Aperturas Mancuernas'),                                      /*9*/                                                             
        ('Aperturas Peck Deck'),                                       /*10*/                                                                                
        ('Fondos en Paralelas'),                                       /*11*/                                                            
                                              
        /* TRÍCEPS */                                              
        ('Press Francés con Barra Z'),                                 /*12*/                 
        ('Press Francés con Mancuernas'),                              /*13*/                    
        ('Extensión de Tríceps en Polea Baja'),                        /*14*/                          
        ('Extensión de Tríceps en Polea Alta'),                        /*15*/                          
        ('JM Press'),                                                  /*16*/
        ('Extensión de Tríceps en Máquina'),                           /*17*/                       
        ('Extensión de Tríceps Unilateral en Polea'),                  /*18*/                                

        /* HOMBRO */
        ('Press Militar con Barra'),                                    /*19*/ 
        ('Press Militar con Mancuernas'),                               /*20*/      
        ('Press de Hombros en Máquina'),                                /*21*/     
        ('Elevaciones Laterales con Mancuernas'),                       /*22*/              
        ('Elevaciones Laterales en Polea Baja'),                        /*23*/             
        ('Elevaciones Laterales en Máquina'),                           /*24*/          
        ('Elevaciones Laterales Unilaterales en Polea'),                /*25*/                     
        ('Pájaros con Mancuernas'),                                     /*26*/
        ('Pájaros en Máquina Peck Deck Inversa'),                       /*27*/              
        ('Pájaros en Polea'),                                           /*28*/     

        /* ESPALDA */
        ('Dominadas Pronadas'),                                        /*29*/    
        ('Dominadas Supinas'),                                         /*30*/   
        ('Dominadas con Agarre Neutro'),                               /*31*/             
        ('Jalón al Pecho con Agarre Pronado'),                         /*32*/                   
        ('Jalón al Pecho con Agarre Supino'),                          /*33*/                  
        ('Jalón al Pecho con Agarre Neutro'),                          /*34*/                  
        ('Jalón al Pecho con Agarre Cerrado'),                         /*35*/                   
        ('Jalón Unilateral en Polea Alta'),                            /*36*/                
        ('Jalón con Brazos Rectos en Polea'),                          /*37*/                  
        ('Remo con Barra'),                                            /*38*/
        ('Remo Pendlay con Barra'),                                    /*39*/        
        ('Remo con Barra T'),                                          /*40*/  
        ('Remo en Máquina con Pecho Apoyado'),                         /*41*/                    
        ('Pullover con Mancuerna'),                                    /*42*/         
        ('Pullover en Polea Alta'),                                    /*43*/         
        ('Peso Muerto Convencional'),                                  /*44*/           
        ('Peso Muerto con Barra Hexagonal'),                           /*45*/                  
        ('Peso Muerto Rumano'),                                        /*46*/     
        ('Buenos Días con Barra'),                                     /*47*/        
        ('Encogimientos de Hombros con Barra'),                        /*48*/                     
        ('Encogimientos de Hombros con Mancuernas'),                   /*49*/                          
        ('Face Pull en Polea'),                                        /*50*/    

        /* BÍCEPS */
        ('Curl de Bíceps con Barra Recta'),                            /*51*/                     
        ('Curl de Bíceps con Barra Z'),                                /*52*/                 
        ('Curl de Bíceps con Mancuernas'),                             /*53*/                    
        ('Curl Martillo con Mancuernas'),                              /*54*/                   
        ('Curl de Bíceps en Banco Scott'),                             /*55*/                    
        ('Curl de Bíceps en Máquina Scott'),                           /*56*/                      
        ('Curl de Bíceps Araña'),                                      /*57*/           
        ('Curl de Bíceps en Máquina'),                                 /*58*/                
        ('Curl de Bíceps Bayesian en Polea Baja'),                     /*59*/                            

        /* PIERNAS */
        ('Sentadilla con Barra'),                                       /*60*/      
        ('Sentadilla Frontal con Barra'),                               /*61*/              
        ('Sentadilla Hack'),                                            /*62*/ 
        ('Sentadilla Búlgara con Mancuernas'),                          /*63*/                   
        ('Prensa de Piernas'),                                          /*64*/   
        ('Prensa de Piernas Unilateral'),                               /*65*/              
        ('Extensión de Cuádriceps en Máquina'),                         /*66*/                    
        ('Extensión de Cuádriceps Unilateral en Máquina'),              /*67*/                               
        ('Curl Femoral Tumbado en Máquina'),                            /*68*/                 
        ('Curl Femoral Sentado en Máquina'),                            /*69*/                 
        ('Hip Thrust con Barra'),                                       /*70*/      
        ('Hip Thrust en Máquina'),                                      /*71*/       
        ('Patada de Glúteo en Polea'),                                  /*72*/           
        ('Patada de Glúteo en Máquina'),                                /*73*/             
        ('Abducción en Máquina'),                                       /*74*/      
        ('Aducción en Máquina'),                                        /*75*/     
        ('Elevación de Talones de Pie'),                                /*76*/             
        ('Elevación de Talones Sentado'),                               /*77*/             
        ('Descanso');                                                   /*78*/   


/* RUTINA 1 PUSH PULL LEGS*/

INSERT INTO dia_rutina(titulo, nombre, orden, rutina_id)
    VALUES 
        ('Push A', 'Pecho, Hombros y Tríceps', 1, 1),
        ('Pull A', 'Espalda y Bíceps', 2, 1),
        ('Legs A', 'Piernas Énfasis Cuádriceps', 3, 1),
        ('Push B', 'Pecho, Hombros y Tríceps', 4, 1),
        ('Pull B', 'Espalda y Bíceps', 5, 1),
        ('Legs B', 'Piernas Énfasis Cadena Posterior', 6, 1),
        ('Descanso', 'Descanso', 7, 1);

/* RUTINA 2 PPL DENSITY */
INSERT INTO dia_rutina(titulo, nombre, orden, rutina_id)
    VALUES 
        ('Push A', 'Pecho, Hombros y Tríceps', 1, 2),
        ('Pull A', 'Espalda y Bíceps', 2, 2),
        ('Legs A', 'Piernas Énfasis Cuádriceps', 3, 2),
        ('Descanso', 'Descanso', 4, 2),
        ('Push B', 'Pecho, Hombros y Tríceps', 5, 2),
        ('Density', 'Cadena Posterior y Espalda', 6, 2),
        ('Descanso', 'Descanso', 7, 2);

/* RUTINA 3 TORSO PIERNA */
INSERT INTO dia_rutina(titulo, nombre, orden, rutina_id)
    VALUES 
        ('TORSO A', 'Pecho y Espalda', 1, 3),
        ('PIERNA A', 'Piernas Énfasis Cuádriceps', 2, 3),
        ('Descanso', 'Descanso', 3, 3),
        ('TORSO B', 'Espalda y Pecho', 4, 3),
        ('PIERNA B', 'Piernas Énfasis Cadena Posterior', 5, 3),
        ('Descanso', 'Descanso', 6, 3),       
        ('Descanso', 'Descanso', 7, 3);

/* RUTINA 4 FULL BODY */
INSERT INTO dia_rutina(titulo, nombre, orden, rutina_id)
    VALUES 
        ('FULL BODY A', 'Torso, Cuádriceps', 1, 4),
        ('Descanso', 'Descanso', 2, 4),
        ('FULL BODY B', 'Cadena Posterior, Pecho', 3, 4),
        ('Descanso', 'Descanso', 4, 4),
        ('FULL BODY C', 'Espalda, Hombros y Piernas', 5, 4),
        ('Descanso', 'Descanso', 6, 4),        
        ('Descanso', 'Descanso', 7, 4);


INSERT INTO ejercicio_rutina(dia_rutina_id, ejercicio_id, orden, series, repeticiones_min, repeticiones_max, descanso_min)
    VALUES 
    /* PUSH PULL LEGS */
        /* PUSH A */
        (1,  1, 1, 3, 6,   8, 3.0), /* Press de Banca con Barra                */
        (1,  5, 2, 3, 8,  10, 3.0), /* Press de Banca Inclinado con Mancuernas */
        (1, 10, 3, 2, 10, 12, 3.0), /* Aperturas Peck Deck                     */
        (1, 20, 4, 2, 8,  10, 3.0), /* Press Militar con Mancuernas            */
        (1, 22, 5, 3, 12, 15, 2.5), /* Elevaciones Laterales con Mancuernas    */
        (1, 15, 6, 3, 10, 12, 2.5), /* Extensión de Tríceps en Polea Alta      */

        /* PULL A */
        (2, 29, 1, 3,  8, 10, 3.0), /* Dominadas Pronadas                    */
        (2, 41, 2, 3, 10, 12, 3.0), /* Remo en Máquina con Pecho Apoyado     */
        (2, 37, 3, 2, 12, 15, 2.5), /* Jalón con Brazos Rectos en Polea      */
        (2, 27, 4, 3, 12, 15, 2.5), /* Pájaros en Máquina Peck Deck Inversa  */
        (2, 52, 5, 3, 10, 12, 3.0), /* Curl de Bíceps con Barra Z            */
        (2, 59, 6, 2, 12, 15, 2.5), /* Curl de Bíceps Bayesian en Polea Baja */

        /* LEGS A */
        (3, 60, 1, 3,  6,  8, 4.0), /* Sentadilla con Barra               */
        (3, 64, 2, 3, 10, 12, 3.0), /* Prensa de Piernas                  */
        (3, 66, 3, 3, 12, 15, 2.5), /* Extensión de Cuádriceps en Máquina */
        (3, 69, 4, 3, 10, 12, 3.0), /* Curl Femoral Sentado en Máquina    */
        (3, 76, 5, 3, 12, 15, 2.5), /* Elevación de Talones de Pie        */
        (3, 75, 6, 2, 12, 15, 2.5), /* Aducción en Máquina                */

        /* PUSH B */
        (4,  4, 1, 3,  8, 10, 3.0), /* Press de Banca Inclinado con Barra       */
        (4,  2, 2, 3, 10, 12, 3.0), /* Press de Banca con Mancuernas            */
        (4,  9, 3, 2, 12, 15, 3.0), /* Aperturas Mancuernas                     */
        (4, 21, 4, 2, 10, 12, 3.0), /* Press de Hombros en Máquina              */
        (4, 23, 5, 3, 12, 15, 2.5), /* Elevaciones Laterales en Polea Baja      */
        (4, 12, 6, 3, 10, 12, 2.5), /* Press Francés con Barra Z                */
        (4, 18, 7, 2, 12, 15, 2.5), /* Extensión de Tríceps Unilateral en Polea */

        /* PULL B */
        (5, 34, 1, 3,  8, 10, 3.0), /* Jalón al Pecho con Agarre Neutro */
        (5, 40, 2, 3, 10, 12, 3.0), /* Remo con Barra T                 */
        (5, 43, 3, 2, 10, 12, 2.5), /* Pullover en Polea Alta           */
        (5, 50, 4, 3, 12, 15, 2.5), /* Face Pull en Polea               */
        (5, 55, 5, 3, 10, 12, 2.5), /* Curl de Bíceps en Banco Scott    */
        (5, 54, 6, 2, 12, 15, 2.5), /* Curl Martillo con Mancuernas     */

        /* LEGS B */
        (6, 46, 1, 3,  8, 10, 4.0), /* Peso Muerto Rumano */
        (6, 62, 2, 3, 10, 12, 4.0), /* */
        (6, 63, 3, 2, 10, 12, 3.0), /* */
        (6, 68, 4, 3, 12, 15, 2.5), /* */
        (6, 70, 5, 3,  8, 10, 3.0), /* */
        (6, 74, 6, 2, 12, 15, 2.5), /* */
        (6, 77, 7, 3, 12, 15, 2.5), /* */

        /* DESCANSO */
        (7, 78, 1, NULL, NULL, NULL, 1440),

    /* PPL DENSITY */
        /* PUSH A */
        (8,  1, 1, 3,  6,  8, 3.0),  /* Press de Banca con Barra             */
        (8,  5, 2, 3,  8, 10, 2.5),  /* Press Inclinado con Mancuernas       */
        (8,  9, 3, 2, 10, 12, 2.5),  /* Aperturas Mancuernas                 */
        (8, 19, 4, 2, 10, 12, 3.0),  /* Press Militar con Barra              */
        (8, 22, 5, 3, 12, 15, 2.5),  /* Elevaciones Laterales con Mancuernas */
        (8, 15, 6, 3, 12, 15, 2.5),  /* Extensión de Tríceps en Polea Alta   */

        /* PULL A */
        (9, 29, 1, 3,  6, 8, 3.0),  /* Dominadas Pronadas                */
        (9, 41, 2, 3,  8,10, 3.0),  /* Remo en Máquina con Pecho Apoyado */
        (9, 36, 3, 2, 10,12, 2.0),  /* Jalón Unilateral en Polea Alta    */
        (9, 50, 4, 2, 12,15, 2.0),  /* Face Pull en Polea                */
        (9, 52, 5, 3, 10,12, 2.5),  /* Curl de Bíceps con Barra Z        */
        (9, 54, 6, 2, 10,12, 2.0),  /* Curl Martillo con Mancuernas      */

        /* LEGS A */
        (10, 62, 1, 3,  8, 10, 3.5),  /* Sentadilla Hack                    */
        (10, 64, 2, 3, 10, 12, 3.0),  /* Prensa de Piernas                  */
        (10, 66, 3, 3, 10, 12, 2.5),  /* Extensión de Cuádriceps en Máquina */
        (10, 69, 4, 3, 10, 12, 2.5),  /* Curl Femoral Sentado en Máquina    */
        (10, 74, 5, 2, 12, 15, 2.5),  /* Abducción en Máquina               */
        (10, 76, 6, 3, 10, 12, 2.0),  /* Elevación de Talones de Pie        */

        /* DESCANSO */
        (11, 78, 1, NULL, NULL, NULL, 1440),

        /* PUSH B */
        (12,  4, 1, 3,  6,  8, 3.0),  /* Press de Banca Inclinado con Barra  */
        (12,  3, 2, 3,  8, 10, 3.0),  /* Press de Banca con Máquina          */
        (12, 21, 3, 2, 10, 12, 2.5),  /* Press de Hombros en Máquina         */
        (12, 23, 4, 3, 12, 15, 2.0),  /* Elevaciones Laterales en Polea Baja */
        (12, 11, 5, 2, 10, 12, 2.0),  /* Fondos en Paralelas                 */
        (12, 12, 6, 3, 12, 15, 2.5),  /* Press Francés con Barra Z           */

        /* DENSITY */
        (13, 44, 1, 3,  4,  6, 3.5),  /* Peso Muerto Convencional                */
        (13, 46, 2, 2,  8, 10, 2.5),  /* Peso Muerto Rumano                      */
        (13, 41, 3, 3, 10, 12, 2.5),  /* Remo en Máquina con Pecho Apoyado       */
        (13, 37, 4, 2, 12, 15, 1.5),  /* Jalón con Brazos Rectos en Polea        */
        (13, 68, 5, 2, 10, 12, 2.0),  /* Curl Femoral Tumbado en Máquina         */
        (13, 49, 6, 2, 10, 12, 1.5),  /* Encogimientos de Hombros con Mancuernas */

        /* DESCANSO */
        (14, 78, 1, NULL, NULL, NULL, 1440),

    /* TORSO PIERNA */
        /* TORSO A — Énfasis en pecho y espalda */
        (15,  2, 1, 3,  8, 10, 3.0),  /* Press de Banca con Mancuernas        */
        (15, 29, 2, 3,  8, 10, 3.0),  /* Dominadas Pronadas                   */
        (15,  5, 3, 3, 10, 12, 3.0),  /* Press Inclinado con Mancuernas       */
        (15, 41, 4, 3,  8, 12, 3.0),  /* Remo en Máquina con Pecho Apoyado    */
        (15, 22, 5, 3, 12, 15, 2.5),  /* Elevaciones Laterales con Mancuernas */
        (15, 52, 6, 2, 10, 12, 2.5),  /* Curl de Bíceps con Barra Z           */
        (15, 15, 7, 2, 10, 12, 2.5),  /* Extensión de Tríceps en Polea Alta   */

        /* PIERNA A — Énfasis en cuádriceps */
        (16, 62, 1, 3,  6,  8, 4.0),  /* Sentadilla Hack                    */
        (16, 64, 2, 3, 10, 12, 3.0),  /* Prensa de Piernas                  */
        (16, 66, 3, 3, 12, 15, 2.5),  /* Extensión de Cuádriceps en Máquina */
        (16, 69, 4, 3, 10, 12, 2.5),  /* Curl Femoral Sentado en Máquina    */
        (16, 74, 5, 2, 12, 15, 2.0),  /* Abducción en Máquina               */
        (16, 76, 6, 3, 10, 12, 2.0),  /* Elevación de Talones de Pie        */

        /* DESCANSO */
        (17, 78, 1, NULL, NULL, NULL, 1440),

        /* TORSO B — Énfasis en espalda y hombros */
        (18,  1, 1, 3,  6,  8, 3.5),  /* Press de Banca con Barra             */
        (18, 38, 2, 3,  8, 10, 3.0),  /* Remo con Barra                       */
        (18,  5, 3, 3, 10, 12, 3.0),  /* Press Inclinado con Máquina          */
        (18, 34, 4, 3, 10, 12, 3.0),  /* Jalón al Pecho con Agarre Neutro     */
        (18, 27, 5, 3, 12, 15, 2.5),  /* Pájaros en Máquina Peck Deck Inversa */
        (18, 23, 6, 3, 12, 15, 2.0),  /* Elevaciones Laterales en Polea Baja  */
        (18, 53, 7, 2, 10, 12, 2.5),  /* Curl de Bíceps con Mancuernas        */
        (18, 16, 8, 2, 10, 12, 2.0),  /* JM Press                             */

        /* PIERNA B — Énfasis en cadena posterior */
        (19, 46, 1, 3,  6, 10, 3.5),  /* Peso Muerto Rumano                */
        (19, 70, 2, 3,  8, 12, 3.0),  /* Hip Thrust con Barra              */
        (19, 68, 3, 3, 10, 15, 2.5),  /* Curl Femoral Tumbado en Máquina   */
        (19, 63, 4, 2, 10, 12, 2.5),  /* Sentadilla Búlgara con Mancuernas */
        (19, 75, 5, 2, 12, 20, 2.0),  /* Aducción en Máquina               */
        (19, 76, 6, 3, 12, 20, 2.0),  /* Elevación de Talones Sentado      */

        /* DESCANSO */
        (20, 78, 1, NULL, NULL, NULL, 1440),

        /* DESCANSO */
        (21, 78, 1, NULL, NULL, NULL, 1440),

/* FULL BODY — 3 DÍAS */
    /* FULL BODY A — Énfasis en fuerza de torso y cuádriceps */   
        (22,  1, 1, 3,  6,  8, 3.0),  /* Press de Banca con Barra             */
        (22, 29, 2, 3,  6, 10, 3.0),  /* Dominadas Pronadas                   */
        (22, 62, 3, 3,  8, 10, 3.5),  /* Sentadilla Hack                      */
        (22, 41, 4, 2,  8, 12, 2.5),  /* Remo en Máquina con Pecho Apoyado    */
        (22, 69, 5, 2, 10, 15, 2.5),  /* Curl Femoral Sentado en Máquina      */
        (22, 22, 6, 2, 12, 20, 2.0),  /* Elevaciones Laterales con Mancuernas */
        (22, 52, 7, 2, 10, 15, 2.5),  /* Curl de Bíceps con Barra Z           */
        (22, 15, 8, 2, 10, 15, 2.5),  /* Extensión de Tríceps en Polea Alta   */

        /* DESCANSO */
        (23, 78, 1, NULL, NULL, NULL, 1440),

    /* FULL BODY B — Énfasis en cadena posterior y pecho inclinado */
        (24, 46, 1, 3,  6, 10, 4.0),  /* Peso Muerto Rumano                      */
        (24,  5, 2, 3,  8, 12, 3.0),  /* Press de Banca Inclinado con Mancuernas */
        (24, 34, 3, 3,  8, 12, 2.5),  /* Jalón al Pecho con Agarre Neutro        */
        (24, 70, 4, 3,  8, 12, 3.0),  /* Hip Thrust con Barra                    */
        (24, 66, 5, 2, 12, 15, 2.5),  /* Extensión de Cuádriceps en Máquina      */
        (24, 27, 6, 2, 12, 20, 2.5),  /* Pájaros en Máquina Peck Deck Inversa    */
        (24, 53, 7, 2, 10, 15, 2.5),  /* Curl de Bíceps con Mancuernas           */
        (24, 12, 8, 2, 10, 15, 2.5),  /* Press Francés con Barra Z               */

        /* DESCANSO */
        (25, 78, 1, NULL, NULL, NULL, 1440),

    /* FULL BODY C — Énfasis en espalda, hombros y piernas */
        (26, 38, 1, 3,  6, 10, 3.0),  /* Remo con Barra                      */
        (26,  3, 2, 3,  8, 12, 3.0),  /* Press de Banca con Máquina          */
        (26, 63, 3, 3, 10, 12, 3.0),  /* Sentadilla Búlgara con Mancuernas   */
        (26, 36, 4, 2, 10, 15, 2.5),  /* Jalón Unilateral en Polea Alta      */
        (26, 68, 5, 2, 10, 15, 2.5),  /* Curl Femoral Tumbado en Máquina     */
        (26, 23, 6, 3, 12, 20, 2.0),  /* Elevaciones Laterales en Polea Baja */
        (26, 76, 7, 3, 10, 15, 2.0),  /* Elevación de Talones de Pie         */
        (26, 54, 8, 2, 10, 15, 2.0),  /* Curl Martillo con Mancuernas        */

        /* DESCANSO */
        (27, 78, 1, NULL, NULL, NULL, 1440),

        /* DESCANSO */
        (28, 78, 1, NULL, NULL, NULL, 1440);
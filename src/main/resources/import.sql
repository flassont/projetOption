--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
 insert into Intervenant (name, surname, password, email, version, admin) values ('Amandine', 'Souilleux','0000', 'amandine@mines-nantes.com',0, true)
 insert into Intervenant (name, surname, password, email, version, admin) values ('Philippe', 'David','0000', 'philippe.david@mines-nantes.fr',0, true)
 insert into RESPONSABILITE (dtype, version,id, intitule, nbEleves, annee, coeffUP) values ('Module', 0, 1, 'Module Test', 25, 2015, 2)
 insert into CategIntervenant (id, nom, annee, version) values (0, 'ECOLE','2015', 0)
 insert into CategIntervenant (id, nom, annee, version) values (1, 'PERM_DPT','2015', 0)
 insert into CATEGINTERVENANT_INTERVENANT (CATEGINTERVENANT_ID, INTERVENANTS_EMAIL) values (0, 'amandine@mines-nantes.com')
 insert into CATEGINTERVENANT_INTERVENANT (CATEGINTERVENANT_ID, INTERVENANTS_EMAIL) values (1, 'philippe.david@mines-nantes.fr')

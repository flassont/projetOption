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
insert into Intervenant (name, surname, password, email, version) values ('Amandine', 'Souilleux','0000', 'amandine@ecole.fr',0),
('Philippe', 'D','0000', 'philippe.d@ecole.fr',0),
('Herve', 'G', '0000', 'herve.g@ecole.fr', 0),
('Mario', 'S', '0000', 'mario.s@ecole.fr', 0),
('Steve', 'S', '0000', 'steve.s@autre.fr', 0),
('Thomas', 'G', '0000', 'thomas.g@encoreuneautre.fr', 0),
('Julien', 'L', '0000', 'julien.l@uneautreencore.fr', 0);

insert into CategIntervenant (version, nom, annee) values
(0, 'ADMIN', 2015),
(0, 'PERM_DPT', 2015),
(0, 'NON_PERM_DPT', 2015),
(0, 'ECOLE', 2015),
(0, 'EXT', 2015);
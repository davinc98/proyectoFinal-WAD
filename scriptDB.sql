

        create table Categoria(
                idCategoria serial primary key,
                nombreCategoria varchar(50) not null,
                descripcionCategoria varchar(50) not null	
        );

        create table Producto(
                idProducto serial primary key,
                nombreProducto varchar(50) not null,
                descripcionProducto varchar(50) not null,
                precio float not null,
                existencia int not null,
                stockMinimo int not null,
                claveCategoria int not null,
                foreign key(claveCategoria) references Categoria(idCategoria) on update cascade on delete cascade
        );


PGDMP     .    7                w         
   SmartStore    11.4    11.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16432 
   SmartStore    DATABASE     �   CREATE DATABASE "SmartStore" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE "SmartStore";
             postgres    false            �            1259    16441    CLIENTE    TABLE     �   CREATE TABLE public."CLIENTE" (
    "CLI_CEDULA" text NOT NULL,
    "CLI_NOMBRE" text,
    "CLI_APELLIDO" text,
    "CLI_CELULAR" text,
    "CLI_CORREO" text,
    "CLI_DIRECCION" text
);
    DROP TABLE public."CLIENTE";
       public         postgres    false            �            1259    16449    DETALLE    TABLE     �   CREATE TABLE public."DETALLE" (
    "DET_CODIGO" numeric NOT NULL,
    "DET_PRECIO" numeric(6,2),
    "DET_CANTIDAD" numeric(3,0),
    "DET_SUBTOTAL" numeric(6,2),
    "PRO_CODIGO" numeric,
    "FAC_CODIGO" numeric NOT NULL
);
    DROP TABLE public."DETALLE";
       public         postgres    false            �            1259    16468    FACTURA    TABLE     �   CREATE TABLE public."FACTURA" (
    "FAC_CODIGO" numeric NOT NULL,
    "FAC_FECHA" date,
    "CLI_CEDULA" text,
    "FAC_SUBTOTAL" numeric(6,2),
    "FAC_IVA" numeric(6,2),
    "FAC_TOTAL" numeric(6,2),
    "FAC_ACTIVO" boolean
);
    DROP TABLE public."FACTURA";
       public         postgres    false            �            1259    16433    PRODUCTO    TABLE     �   CREATE TABLE public."PRODUCTO" (
    "PRO_CODIGO" numeric NOT NULL,
    "PRO_NOMBRE" text,
    "PRO_PRECIO" numeric(6,2),
    "PRO_DESCRIPCION" text
);
    DROP TABLE public."PRODUCTO";
       public         postgres    false                      0    16441    CLIENTE 
   TABLE DATA               }   COPY public."CLIENTE" ("CLI_CEDULA", "CLI_NOMBRE", "CLI_APELLIDO", "CLI_CELULAR", "CLI_CORREO", "CLI_DIRECCION") FROM stdin;
    public       postgres    false    197   �                 0    16449    DETALLE 
   TABLE DATA               {   COPY public."DETALLE" ("DET_CODIGO", "DET_PRECIO", "DET_CANTIDAD", "DET_SUBTOTAL", "PRO_CODIGO", "FAC_CODIGO") FROM stdin;
    public       postgres    false    198   R                 0    16468    FACTURA 
   TABLE DATA               �   COPY public."FACTURA" ("FAC_CODIGO", "FAC_FECHA", "CLI_CEDULA", "FAC_SUBTOTAL", "FAC_IVA", "FAC_TOTAL", "FAC_ACTIVO") FROM stdin;
    public       postgres    false    199   o                 0    16433    PRODUCTO 
   TABLE DATA               a   COPY public."PRODUCTO" ("PRO_CODIGO", "PRO_NOMBRE", "PRO_PRECIO", "PRO_DESCRIPCION") FROM stdin;
    public       postgres    false    196   �       �
           2606    16448    CLIENTE CLIENTE_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."CLIENTE"
    ADD CONSTRAINT "CLIENTE_pkey" PRIMARY KEY ("CLI_CEDULA");
 B   ALTER TABLE ONLY public."CLIENTE" DROP CONSTRAINT "CLIENTE_pkey";
       public         postgres    false    197            �
           2606    16456    DETALLE DETALLE_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."DETALLE"
    ADD CONSTRAINT "DETALLE_pkey" PRIMARY KEY ("DET_CODIGO");
 B   ALTER TABLE ONLY public."DETALLE" DROP CONSTRAINT "DETALLE_pkey";
       public         postgres    false    198            �
           2606    16475    FACTURA FACTURA_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."FACTURA"
    ADD CONSTRAINT "FACTURA_pkey" PRIMARY KEY ("FAC_CODIGO");
 B   ALTER TABLE ONLY public."FACTURA" DROP CONSTRAINT "FACTURA_pkey";
       public         postgres    false    199            �
           2606    16440    PRODUCTO PRODUCTO_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."PRODUCTO"
    ADD CONSTRAINT "PRODUCTO_pkey" PRIMARY KEY ("PRO_CODIGO");
 D   ALTER TABLE ONLY public."PRODUCTO" DROP CONSTRAINT "PRODUCTO_pkey";
       public         postgres    false    196            �
           1259    16487    fki_CLI_CEDULA    INDEX     N   CREATE INDEX "fki_CLI_CEDULA" ON public."FACTURA" USING btree ("CLI_CEDULA");
 $   DROP INDEX public."fki_CLI_CEDULA";
       public         postgres    false    199            �
           1259    16481    fki_FAC_CODIGO    INDEX     N   CREATE INDEX "fki_FAC_CODIGO" ON public."DETALLE" USING btree ("FAC_CODIGO");
 $   DROP INDEX public."fki_FAC_CODIGO";
       public         postgres    false    198            �
           1259    16467    fki_PRO_CODIGO    INDEX     N   CREATE INDEX "fki_PRO_CODIGO" ON public."DETALLE" USING btree ("PRO_CODIGO");
 $   DROP INDEX public."fki_PRO_CODIGO";
       public         postgres    false    198            �
           2606    16482    FACTURA CLI_CEDULA    FK CONSTRAINT     �   ALTER TABLE ONLY public."FACTURA"
    ADD CONSTRAINT "CLI_CEDULA" FOREIGN KEY ("CLI_CEDULA") REFERENCES public."CLIENTE"("CLI_CEDULA");
 @   ALTER TABLE ONLY public."FACTURA" DROP CONSTRAINT "CLI_CEDULA";
       public       postgres    false    2702    197    199            �
           2606    16476    DETALLE FAC_CODIGO    FK CONSTRAINT     �   ALTER TABLE ONLY public."DETALLE"
    ADD CONSTRAINT "FAC_CODIGO" FOREIGN KEY ("FAC_CODIGO") REFERENCES public."FACTURA"("FAC_CODIGO");
 @   ALTER TABLE ONLY public."DETALLE" DROP CONSTRAINT "FAC_CODIGO";
       public       postgres    false    2708    199    198            �
           2606    16462    DETALLE PRO_CODIGO    FK CONSTRAINT     �   ALTER TABLE ONLY public."DETALLE"
    ADD CONSTRAINT "PRO_CODIGO" FOREIGN KEY ("PRO_CODIGO") REFERENCES public."PRODUCTO"("PRO_CODIGO");
 @   ALTER TABLE ONLY public."DETALLE" DROP CONSTRAINT "PRO_CODIGO";
       public       postgres    false    198    2700    196               I   x�304�BNה�Ģ�|N����DNK�L	8��&f��%��r:���%'r� �wbQj�F�0��=... �'!�            x������ � �            x������ � �         f   x�3�t,*ʯ�44�30���r��8K���4�35�0R��S3KR��9�J��8M����r :�L8����4�9�u�^������ ��#     
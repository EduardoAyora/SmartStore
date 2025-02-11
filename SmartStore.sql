PGDMP     2    .    
            w         
   SmartStore    11.4    11.4      ,           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            -           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            .           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            /           1262    24772 
   SmartStore    DATABASE     �   CREATE DATABASE "SmartStore" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Ecuador.1252' LC_CTYPE = 'Spanish_Ecuador.1252';
    DROP DATABASE "SmartStore";
             postgres    false            �            1259    24773    CLIENTE    TABLE     �   CREATE TABLE public."CLIENTE" (
    "CLI_CEDULA" text NOT NULL,
    "CLI_NOMBRE" text,
    "CLI_APELLIDO" text,
    "CLI_CELULAR" text,
    "CLI_CORREO" text,
    "CLI_DIRECCION" text,
    "CLI_TARJETA" text,
    "CLI_SALDO" numeric
);
    DROP TABLE public."CLIENTE";
       public         postgres    false            �            1259    24779    DETALLE    TABLE     �   CREATE TABLE public."DETALLE" (
    "DET_CODIGO" numeric NOT NULL,
    "DET_PRECIO" numeric(6,2),
    "DET_CANTIDAD" numeric(3,0),
    "DET_SUBTOTAL" numeric(6,2),
    "PRO_CODIGO" numeric,
    "FAC_CODIGO" numeric NOT NULL
);
    DROP TABLE public."DETALLE";
       public         postgres    false            �            1259    24785    ESTANTE    TABLE     {   CREATE TABLE public."ESTANTE" (
    "EST_CODIGO" numeric NOT NULL,
    "PRO_CODIGO" numeric,
    "EST_CANTIDAD" numeric
);
    DROP TABLE public."ESTANTE";
       public         postgres    false            �            1259    24791    FACTURA    TABLE     �   CREATE TABLE public."FACTURA" (
    "FAC_CODIGO" numeric NOT NULL,
    "FAC_FECHA" date,
    "CLI_CEDULA" text,
    "FAC_SUBTOTAL" numeric(6,2),
    "FAC_IVA" numeric(6,2),
    "FAC_TOTAL" numeric(6,2),
    "FAC_ACTIVO" boolean
);
    DROP TABLE public."FACTURA";
       public         postgres    false            �            1259    24797    PIN    TABLE     [   CREATE TABLE public."PIN" (
    "PIN_CODIGO" numeric NOT NULL,
    "EST_CODIGO" numeric
);
    DROP TABLE public."PIN";
       public         postgres    false            �            1259    24803    PRODUCTO    TABLE     �   CREATE TABLE public."PRODUCTO" (
    "PRO_CODIGO" numeric NOT NULL,
    "PRO_NOMBRE" text,
    "PRO_PRECIO" numeric(6,2),
    "PRO_DESCRIPCION" text
);
    DROP TABLE public."PRODUCTO";
       public         postgres    false            $          0    24773    CLIENTE 
   TABLE DATA               �   COPY public."CLIENTE" ("CLI_CEDULA", "CLI_NOMBRE", "CLI_APELLIDO", "CLI_CELULAR", "CLI_CORREO", "CLI_DIRECCION", "CLI_TARJETA", "CLI_SALDO") FROM stdin;
    public       postgres    false    196   �#       %          0    24779    DETALLE 
   TABLE DATA               {   COPY public."DETALLE" ("DET_CODIGO", "DET_PRECIO", "DET_CANTIDAD", "DET_SUBTOTAL", "PRO_CODIGO", "FAC_CODIGO") FROM stdin;
    public       postgres    false    197   �$       &          0    24785    ESTANTE 
   TABLE DATA               O   COPY public."ESTANTE" ("EST_CODIGO", "PRO_CODIGO", "EST_CANTIDAD") FROM stdin;
    public       postgres    false    198   y%       '          0    24791    FACTURA 
   TABLE DATA               �   COPY public."FACTURA" ("FAC_CODIGO", "FAC_FECHA", "CLI_CEDULA", "FAC_SUBTOTAL", "FAC_IVA", "FAC_TOTAL", "FAC_ACTIVO") FROM stdin;
    public       postgres    false    199   �%       (          0    24797    PIN 
   TABLE DATA               ;   COPY public."PIN" ("PIN_CODIGO", "EST_CODIGO") FROM stdin;
    public       postgres    false    200   �&       )          0    24803    PRODUCTO 
   TABLE DATA               a   COPY public."PRODUCTO" ("PRO_CODIGO", "PRO_NOMBRE", "PRO_PRECIO", "PRO_DESCRIPCION") FROM stdin;
    public       postgres    false    201   �&       �
           2606    24810    CLIENTE CLIENTE_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."CLIENTE"
    ADD CONSTRAINT "CLIENTE_pkey" PRIMARY KEY ("CLI_CEDULA");
 B   ALTER TABLE ONLY public."CLIENTE" DROP CONSTRAINT "CLIENTE_pkey";
       public         postgres    false    196            �
           2606    24812    DETALLE DETALLE_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."DETALLE"
    ADD CONSTRAINT "DETALLE_pkey" PRIMARY KEY ("DET_CODIGO");
 B   ALTER TABLE ONLY public."DETALLE" DROP CONSTRAINT "DETALLE_pkey";
       public         postgres    false    197            �
           2606    24814    ESTANTE ESTANTE_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."ESTANTE"
    ADD CONSTRAINT "ESTANTE_pkey" PRIMARY KEY ("EST_CODIGO");
 B   ALTER TABLE ONLY public."ESTANTE" DROP CONSTRAINT "ESTANTE_pkey";
       public         postgres    false    198            �
           2606    24816    FACTURA FACTURA_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."FACTURA"
    ADD CONSTRAINT "FACTURA_pkey" PRIMARY KEY ("FAC_CODIGO");
 B   ALTER TABLE ONLY public."FACTURA" DROP CONSTRAINT "FACTURA_pkey";
       public         postgres    false    199            �
           2606    24818    PIN PIN_CODIGO 
   CONSTRAINT     Z   ALTER TABLE ONLY public."PIN"
    ADD CONSTRAINT "PIN_CODIGO" PRIMARY KEY ("PIN_CODIGO");
 <   ALTER TABLE ONLY public."PIN" DROP CONSTRAINT "PIN_CODIGO";
       public         postgres    false    200            �
           2606    24820    PRODUCTO PRODUCTO_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."PRODUCTO"
    ADD CONSTRAINT "PRODUCTO_pkey" PRIMARY KEY ("PRO_CODIGO");
 D   ALTER TABLE ONLY public."PRODUCTO" DROP CONSTRAINT "PRODUCTO_pkey";
       public         postgres    false    201            �
           1259    24821    fki_CLI_CEDULA    INDEX     N   CREATE INDEX "fki_CLI_CEDULA" ON public."FACTURA" USING btree ("CLI_CEDULA");
 $   DROP INDEX public."fki_CLI_CEDULA";
       public         postgres    false    199            �
           1259    24822    fki_EST_CODIGO    INDEX     J   CREATE INDEX "fki_EST_CODIGO" ON public."PIN" USING btree ("EST_CODIGO");
 $   DROP INDEX public."fki_EST_CODIGO";
       public         postgres    false    200            �
           1259    24823    fki_FAC_CODIGO    INDEX     N   CREATE INDEX "fki_FAC_CODIGO" ON public."DETALLE" USING btree ("FAC_CODIGO");
 $   DROP INDEX public."fki_FAC_CODIGO";
       public         postgres    false    197            �
           1259    24824    fki_PRO_CODIGO    INDEX     N   CREATE INDEX "fki_PRO_CODIGO" ON public."DETALLE" USING btree ("PRO_CODIGO");
 $   DROP INDEX public."fki_PRO_CODIGO";
       public         postgres    false    197            �
           1259    24825    fki_PRO_CODIGO.    INDEX     O   CREATE INDEX "fki_PRO_CODIGO." ON public."ESTANTE" USING btree ("PRO_CODIGO");
 %   DROP INDEX public."fki_PRO_CODIGO.";
       public         postgres    false    198            �
           2606    24826    FACTURA CLI_CEDULA    FK CONSTRAINT     �   ALTER TABLE ONLY public."FACTURA"
    ADD CONSTRAINT "CLI_CEDULA" FOREIGN KEY ("CLI_CEDULA") REFERENCES public."CLIENTE"("CLI_CEDULA");
 @   ALTER TABLE ONLY public."FACTURA" DROP CONSTRAINT "CLI_CEDULA";
       public       postgres    false    199    196    2710            �
           2606    24831    PIN EST_CODIGO    FK CONSTRAINT     �   ALTER TABLE ONLY public."PIN"
    ADD CONSTRAINT "EST_CODIGO" FOREIGN KEY ("EST_CODIGO") REFERENCES public."ESTANTE"("EST_CODIGO");
 <   ALTER TABLE ONLY public."PIN" DROP CONSTRAINT "EST_CODIGO";
       public       postgres    false    198    200    2716            �
           2606    24836    DETALLE FAC_CODIGO    FK CONSTRAINT     �   ALTER TABLE ONLY public."DETALLE"
    ADD CONSTRAINT "FAC_CODIGO" FOREIGN KEY ("FAC_CODIGO") REFERENCES public."FACTURA"("FAC_CODIGO");
 @   ALTER TABLE ONLY public."DETALLE" DROP CONSTRAINT "FAC_CODIGO";
       public       postgres    false    197    199    2719            �
           2606    24841    DETALLE PRO_CODIGO    FK CONSTRAINT     �   ALTER TABLE ONLY public."DETALLE"
    ADD CONSTRAINT "PRO_CODIGO" FOREIGN KEY ("PRO_CODIGO") REFERENCES public."PRODUCTO"("PRO_CODIGO");
 @   ALTER TABLE ONLY public."DETALLE" DROP CONSTRAINT "PRO_CODIGO";
       public       postgres    false    201    2725    197            �
           2606    24846    ESTANTE PRO_CODIGO.    FK CONSTRAINT     �   ALTER TABLE ONLY public."ESTANTE"
    ADD CONSTRAINT "PRO_CODIGO." FOREIGN KEY ("PRO_CODIGO") REFERENCES public."PRODUCTO"("PRO_CODIGO");
 A   ALTER TABLE ONLY public."ESTANTE" DROP CONSTRAINT "PRO_CODIGO.";
       public       postgres    false    201    198    2725            $   �   x�e���0�g�a�؉����+�Dԥ)�H�x{Z���'��w�
��Wn]�㻶&m@^��m�塯u��D��"�\J1�&�)(Cp�M��Z��ĝ�>�'��KCJ�h�k+��}�"��dl�(l���y`��R�߁�۔��NǨ.Z)��G�      %   �   x�e�An!C��aF�	s���e>�JM��z�0@�՛��]���XKo�U.�7&��P��M	}S�������O;f�5����z�LܲT�	z10���$��P`��3��0�e�C4��G֑t�[��y�0�P�*Y(gQ�����'�ϲD�c�?f^�9�ssG޹��;�:�37�s�n�aC      &   &   x�3�4�4��2�4��2�4�4��2�B#�=... E8      '   �   x���Y
�0D��Th�v�#��RC�bR"��x�Q�xި߸���֡$���A��G�&30E��(�ð*�� ��"yg�>�Q�@�:���݀�9�TZ��~�v[x�_������Hd4G5�Q�u�����~��r}Z��U�2����YX�4�YL�d��XL��F$<"V��Z���*�R�RZI��E6�ҳh���e�|�~���KM5�[(��!m�qѻc)���'      (   6   x�Ĺ�0�Z�gJ�]��&�A����Hl9r���KW�`����jt	�      )   i   x�3�N,J��K�4�35�q�Rs��8K���4ɀ�
�y
�ɩ�%�\Ɯ!�W5�
�9�\&�^���
)�9
e�99�@��hb
�
9�%E�\1z\\\ �x&�     
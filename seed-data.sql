--
-- PostgreSQL database dump
--

\restrict R6EESyThggZL83gEUSjknsoVS0nJgDzdr6SSsdXHaP8uYAsfWzK5l6jlbq8PV8P

-- Dumped from database version 17.11
-- Dumped by pg_dump version 17.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: account_holder; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account_holder VALUES ('696a38ad-7730-4cea-9cf8-d616bcf598b2', 'ACC-2026-001', 'Alice', 'Keza', '0788200001');
INSERT INTO public.account_holder VALUES ('72ac2bba-80a6-4cb4-a401-10b83448846e', 'ACC-2026-002', 'Bob', 'Keza', '0788200002');
INSERT INTO public.account_holder VALUES ('beff1bb9-70c6-4753-94a2-09b7efe38b86', 'ACC-2026-003', 'Chantal', 'Mugisha', '0788200003');
INSERT INTO public.account_holder VALUES ('ec392463-efe3-4c78-97a2-4f550089e59c', 'ACC-2026-004', 'David', 'Uwase', '0788200004');
INSERT INTO public.account_holder VALUES ('6b887418-6688-46ec-8b82-f4d2cbd9eb83', 'ACC-2026-005', 'Esther', 'Habimana', '0788200005');


--
-- Data for Name: merchant_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.merchant_code VALUES ('f6ccdd55-7e03-47f5-b455-17eec09e71f6', 10101, 'MoMo code 10101');
INSERT INTO public.merchant_code VALUES ('3ed56757-b236-46ff-9edb-7a636fdfe043', 10102, 'MoMo code 10102');
INSERT INTO public.merchant_code VALUES ('458ec2bb-1b18-4307-8c69-08c9f2f04865', 10103, 'MoMo code 10103');
INSERT INTO public.merchant_code VALUES ('b4c1abae-7180-4cdb-b89d-ace6d5a5dfb7', 10104, 'MoMo code 10104');


--
-- Data for Name: merchant; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.merchant VALUES ('bfce8068-e43e-4b69-8e35-478d1eb59d15', 'simba', 'Simba Supermarket', '0788100001', 'f6ccdd55-7e03-47f5-b455-17eec09e71f6');
INSERT INTO public.merchant VALUES ('eba1639a-f300-43cd-b6d0-3623779b6cdf', 'yego', 'Yego Moto', '0788100002', '3ed56757-b236-46ff-9edb-7a636fdfe043');
INSERT INTO public.merchant VALUES ('970e03a7-bb27-4e11-9f18-7cc206081ea4', 'mtn', 'MTN Kigali Shop', '0788100003', '458ec2bb-1b18-4307-8c69-08c9f2f04865');


--
-- Data for Name: spending_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.spending_category VALUES ('163f2119-8b71-40b1-8b80-afdd6b001448', 'Food');
INSERT INTO public.spending_category VALUES ('35a14842-347e-42c2-ba7e-668feaae1ccd', 'Transport');
INSERT INTO public.spending_category VALUES ('1a338609-e260-4520-91f3-2760020629f8', 'Airtime');
INSERT INTO public.spending_category VALUES ('62327c24-fc76-43de-b24b-7d4308bf35ef', 'Rent');


--
-- Data for Name: merchant_spending_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.merchant_spending_category VALUES ('bfce8068-e43e-4b69-8e35-478d1eb59d15', '163f2119-8b71-40b1-8b80-afdd6b001448');
INSERT INTO public.merchant_spending_category VALUES ('bfce8068-e43e-4b69-8e35-478d1eb59d15', '1a338609-e260-4520-91f3-2760020629f8');
INSERT INTO public.merchant_spending_category VALUES ('eba1639a-f300-43cd-b6d0-3623779b6cdf', '35a14842-347e-42c2-ba7e-668feaae1ccd');
INSERT INTO public.merchant_spending_category VALUES ('970e03a7-bb27-4e11-9f18-7cc206081ea4', '1a338609-e260-4520-91f3-2760020629f8');


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaction VALUES ('f39fc82c-f09d-4db4-9aed-bae6f1ebb654', 12500, 'Paid 12500 RWF to Simba Supermarket', 'REVIEWED', '2026-10-05', '696a38ad-7730-4cea-9cf8-d616bcf598b2', 'bfce8068-e43e-4b69-8e35-478d1eb59d15');
INSERT INTO public.transaction VALUES ('d7fc2658-c2c6-48fc-8256-21ce4a455f0b', 8000, 'Paid 8000 RWF to Simba Supermarket', 'CATEGORIZED', '2026-10-20', '696a38ad-7730-4cea-9cf8-d616bcf598b2', 'bfce8068-e43e-4b69-8e35-478d1eb59d15');
INSERT INTO public.transaction VALUES ('a4451466-bec9-4c95-beef-00e26816da29', 3000, 'Paid 3000 RWF to Simba Supermarket', 'IGNORED', '2026-10-20', '696a38ad-7730-4cea-9cf8-d616bcf598b2', 'bfce8068-e43e-4b69-8e35-478d1eb59d15');
INSERT INTO public.transaction VALUES ('f7877dba-a6d7-4157-83b6-dd783640f0b7', 1500, 'Paid 1500 RWF to Yego Moto', 'IMPORTED', '2026-11-02', '696a38ad-7730-4cea-9cf8-d616bcf598b2', 'eba1639a-f300-43cd-b6d0-3623779b6cdf');
INSERT INTO public.transaction VALUES ('a219f764-5f86-4347-aa5f-4794bc0f79c3', 2000, 'Paid 2000 RWF to Yego Moto', 'REVIEWED', '2026-10-12', '72ac2bba-80a6-4cb4-a401-10b83448846e', 'eba1639a-f300-43cd-b6d0-3623779b6cdf');
INSERT INTO public.transaction VALUES ('f3bfdd85-271a-4d2f-83f2-682c75cad676', 5000, 'Paid 5000 RWF to MTN Kigali Shop', 'IMPORTED', '2026-11-10', '72ac2bba-80a6-4cb4-a401-10b83448846e', '970e03a7-bb27-4e11-9f18-7cc206081ea4');
INSERT INTO public.transaction VALUES ('d9d146a0-dc1b-4812-b222-630812c89f1c', 1000, 'Paid 1000 RWF to MTN Kigali Shop', 'CATEGORIZED', '2026-10-28', 'beff1bb9-70c6-4753-94a2-09b7efe38b86', '970e03a7-bb27-4e11-9f18-7cc206081ea4');
INSERT INTO public.transaction VALUES ('809be8ed-1095-4455-a138-36c21c3f9f87', 15000, 'Paid 15000 RWF to Simba Supermarket', 'IMPORTED', '2026-11-15', 'beff1bb9-70c6-4753-94a2-09b7efe38b86', 'bfce8068-e43e-4b69-8e35-478d1eb59d15');
INSERT INTO public.transaction VALUES ('90de6acd-5b65-41f7-8f03-e46bdd3cc1f4', 2500, 'Paid 2500 RWF to Yego Moto', 'IGNORED', '2026-10-18', 'ec392463-efe3-4c78-97a2-4f550089e59c', 'eba1639a-f300-43cd-b6d0-3623779b6cdf');
INSERT INTO public.transaction VALUES ('e5490ea5-3403-48a9-b423-ce06cf112816', 3000, 'Paid 3000 RWF to MTN Kigali Shop', 'REVIEWED', '2026-11-20', '6b887418-6688-46ec-8b82-f4d2cbd9eb83', '970e03a7-bb27-4e11-9f18-7cc206081ea4');


--
-- Data for Name: receipt_line; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- PostgreSQL database dump complete
--

\unrestrict R6EESyThggZL83gEUSjknsoVS0nJgDzdr6SSsdXHaP8uYAsfWzK5l6jlbq8PV8P


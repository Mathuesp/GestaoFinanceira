COPY registro(data_compra, data_vencimento, descricao, parcela, tipo_conta, valor, categoria_id, usuario_id)
FROM 'C:\Users\Public\Downloads\Gestao_Finan.csv'
DELIMITER ';'
CSV HEADER;
import joblib
import pandas as pd
import ast
from sklearn.preprocessing import MultiLabelBinarizer
from sklearn.feature_extraction.text import TfidfVectorizer
import os
import sys

args = sys.argv[1:]
skills = [' '.join(args)]

loaded_model = joblib.load(os.path.abspath("C:\\Users\\nikit\\Diploma\\Server\\NLP_model\\model\\skills_analyze_model.pkl"))

df = pd.read_csv(os.path.abspath("C:\\Users\\nikit\\Diploma\\Server\\NLP_model\\dataset\\large_dataset_with_labels.csv"), index_col=0)
df['label'] = df['label'].apply(lambda x: '[' + '\'' + str(x)[1:-1] + '\'' + ']')
df['label'] = df['label'].apply(lambda x: ast.literal_eval(x))

multilabel = MultiLabelBinarizer()
multilabel.fit_transform(df['label'])

tfidf = TfidfVectorizer(analyzer='word', max_features=10000, ngram_range=(1,1))
tfidf.fit_transform(df['description'])

foramated_skills = tfidf.transform(skills)
print(multilabel.inverse_transform(loaded_model.predict(foramated_skills)))

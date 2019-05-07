using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
namespace Msekinci
{
    #region Veteriner_sorular
    public class Veteriner_sorular
    {
        #region Member Variables
        protected int _id;
        protected string _mus_id;
        protected string _soru;
        protected string _durum;
        #endregion
        #region Constructors
        public Veteriner_sorular() { }
        public Veteriner_sorular(string mus_id, string soru, string durum)
        {
            this._mus_id=mus_id;
            this._soru=soru;
            this._durum=durum;
        }
        #endregion
        #region Public Properties
        public virtual int Id
        {
            get {return _id;}
            set {_id=value;}
        }
        public virtual string Mus_id
        {
            get {return _mus_id;}
            set {_mus_id=value;}
        }
        public virtual string Soru
        {
            get {return _soru;}
            set {_soru=value;}
        }
        public virtual string Durum
        {
            get {return _durum;}
            set {_durum=value;}
        }
        #endregion
    }
    #endregion
}
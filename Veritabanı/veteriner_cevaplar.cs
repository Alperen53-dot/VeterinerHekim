using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
namespace Msekinci
{
    #region Veteriner_cevaplar
    public class Veteriner_cevaplar
    {
        #region Member Variables
        protected int _id;
        protected string _mus_id;
        protected string _soru_id;
        protected string _cevap;
        #endregion
        #region Constructors
        public Veteriner_cevaplar() { }
        public Veteriner_cevaplar(string mus_id, string soru_id, string cevap)
        {
            this._mus_id=mus_id;
            this._soru_id=soru_id;
            this._cevap=cevap;
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
        public virtual string Soru_id
        {
            get {return _soru_id;}
            set {_soru_id=value;}
        }
        public virtual string Cevap
        {
            get {return _cevap;}
            set {_cevap=value;}
        }
        #endregion
    }
    #endregion
}